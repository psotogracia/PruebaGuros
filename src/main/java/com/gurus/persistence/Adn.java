package com.gurus.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Adn")

public class Adn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private long id;
	
	@Column
	private String adn;
	
	@Column
	private boolean mutation;

	public boolean isMutation() {
		return mutation;
	}

	public void setMutation(boolean mutation) {
		this.mutation = mutation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdn() {
		return adn;
	}

	public void setAdn(String adn) {
		this.adn = adn;
	}
	
	
	public static Adn getInstance(String jsonArray, boolean isMutation) {
		
		Adn adn = new Adn();
		adn.setAdn(jsonArray);
		adn.setMutation(isMutation);
		adn.setId(Math.abs(adn.hashCode()));
		
		return adn;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adn == null) ? 0 : adn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adn other = (Adn) obj;
		if (adn == null) {
			if (other.adn != null)
				return false;
		} else if (!adn.equals(other.adn))
			return false;
		return true;
	}


	
}
