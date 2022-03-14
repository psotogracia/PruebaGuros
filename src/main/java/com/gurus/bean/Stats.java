package com.gurus.bean;

public class Stats {

	private int countMutations;
	private int countNoMutation;
	private  float ratio;
	
	public void incrementMutation() {
		this.countMutations++;
	}
	
	public void incrementNoMutatio() {
		this.countNoMutation++;
	}
	
	public void calculateRatio() {
		if(this.countNoMutation>0) {
			this.ratio = this.countMutations / this.countNoMutation;
		} else {
			this.ratio = 100;
		}
	}
	
	public int getCountMutations() {
		return countMutations;
	}
	public void setCountMutations(int countMutations) {
		this.countMutations = countMutations;
	}
	public int getCountNoMutation() {
		return countNoMutation;
	}
	public void setCountNoMutation(int countNoMutation) {
		this.countNoMutation = countNoMutation;
	}
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	
	
	
}
