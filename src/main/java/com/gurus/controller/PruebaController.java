package com.gurus.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gurus.bean.DnaRequest;
import com.gurus.bean.Stats;
import com.gurus.persistence.Adn;
import com.gurus.services.DnaService;

@RestController
@RequestMapping("/prubaGuru")

public class PruebaController {
	
	@Autowired
	private DnaService dnaService;
	
	
	@PostMapping("/isMutation")
	public ResponseEntity<?> isMutatation (@RequestBody DnaRequest dnaRequest) {
		
		if(dnaService.hasMutation(dnaRequest.getDna())) {
			dnaService.save(dnaRequest.getDna(), true);
			return ResponseEntity.ok().build();
		}else {
			dnaService.save(dnaRequest.getDna(), false);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			
		}
		
	}
	
	@GetMapping("/stats")
	public Stats consultMutation() {
		
		return dnaService.getStats();
	}
	
	

}
