package com.gurus.services;

import java.util.ArrayList;

import com.gurus.bean.Stats;
import com.gurus.persistence.Adn;

public interface DnaService {

	public boolean hasMutation(ArrayList<String> dna);
	
	public void save (ArrayList<String> dna, boolean mutation);
	
	public ArrayList<Adn> getAll();
	
	public Stats getStats();
}
