package com.gurus.services.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.gurus.bean.Stats;
import com.gurus.persistence.Adn;
import com.gurus.persistence.Repository;
import com.gurus.services.DnaService;

@Service
public class DnaServiceImpl implements DnaService {
	
	@Autowired
	private Repository repository;
	
	@Override
	public boolean hasMutation(ArrayList<String> dna) {
		
		char[][] matrix = listToMatrix(dna);
		
		if(validateHorizontal(dna) || (validateVertical(dna, matrix)) || (validateDiagonal(dna, matrix))) {
			return true;
		}	
		
		return false;
	}
	
	private boolean validateDiagonal (ArrayList<String> dna, char[][] matrix) {

		
		int counter = 1;
		for(int row = 1; row < dna.size();row++){
			if(matrix[row][row] == matrix[row-1][row-1]) {
				counter++;
			}else {
            	counter = 1;
            }
            
            if (counter == 4) {
            	return true;
            } 
            
        }
				
		return false;
	}
	
	private boolean validateVertical (ArrayList<String> dna, char[][] matrix) {
		
        for (int i = 0; i < dna.size(); i++) {
        	int counter = 1;
            for (int j = 1; j < dna.size(); j++) {        
                if (matrix[j][i] == matrix[j - 1][i]) {
                	counter++; 
                }else {
                	counter = 1;
                }
                if (counter == 4) {
	            	return true;	
	            	}
            }
        }
                
		return false;
	}
	
	private boolean validateHorizontal(ArrayList<String> dna) {
			
		for (Iterator iterator = dna.iterator(); iterator.hasNext();) {
			int counter = 1;
			char[] charArray = ((String)iterator.next()).toCharArray();
		    for (int i = 1; i < charArray.length; i++) {
	            if (charArray[i] == charArray[i - 1]) {
	            	counter++; 
	            } 	
	            	else {
	            		counter = 1;
	            	} 
	            
	            if (counter == 4) {
	            	return true;	
	            	}
	        	}
		}	

		return false;
	}

	private char[][] listToMatrix(ArrayList<String> dna){
		 
        char[][] matrix = new char[dna.size()][dna.get(0).length()];
        for (int i = 0; i < dna.size(); i++) {
            for (int j = 0; j < dna.get(i).length(); j++) {
                
                matrix[i][j] = dna.get(i).toCharArray()[j];
            }
        }
        return matrix;
	}

	
	@Override
	public void save(ArrayList<String> dna, boolean mutation) {
		
		String json = new Gson().toJson(dna);
		repository.save(Adn.getInstance(json, mutation));
		
		
	}

	@Override
	public ArrayList<Adn> getAll() {
		
		return (ArrayList<Adn>) repository.findAll();
	}

	@Override
	public Stats getStats() {
		Stats stats = new Stats();
		
		this.getAll().stream().forEach(item->{
			if(item.isMutation()) {
				stats.incrementMutation();
			}else {
				stats.incrementNoMutatio();
			}
		});
		stats.calculateRatio();
		return stats;
	}
	
	
	
	
}
