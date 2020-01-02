package com.revature.model;

import java.util.ArrayList;
import com.revature.service.Service;

public class Screen {
	protected String screenName;
	protected String intro;
	protected String prompt;
	protected ArrayList<String> options = new ArrayList<String>();
	protected Boolean finished;
	protected boolean hasIntro;
	protected Service service;
	
	public Screen(String newName, Service service) {
		super();
		this.screenName = newName;
		this.finished = false;
		this.service = service;
	}
	
	void displayIntro() {
		if(hasIntro) System.out.println(intro);
	}
	
	protected void resetPrompts() {
	  finished = false;
	}
	
	public void show() {
		resetPrompts();
	}
	
	void displayInputInstructions() {
	}
	
	protected void interpretOption(String str) {
	}
	
	public String getScreenName() {
		return screenName;
	}
	
	public void setService(Service service) {
	  this.service = service;
	}
}
