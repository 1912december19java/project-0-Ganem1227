package com.revature.model;

import java.util.ArrayList;

public class Screen {
	protected String screenName;
	protected String intro;
	protected String prompt;
	protected ArrayList<String> options = new ArrayList<String>();
	protected Boolean finished;
	protected boolean hasIntro;
	
	public Screen(String newName) {
		super();
		this.screenName = newName;
		this.finished = false;
	}
	
	void displayIntro() {
		if(hasIntro) System.out.println(intro);
	}
	
	
	public void show() {
		finished = false;
	}
	
	void displayInputInstructions() {
	}
	
	protected void interpretOption(int i) {
	}
	
	public String getScreenName() {
		return screenName;
	}
}
