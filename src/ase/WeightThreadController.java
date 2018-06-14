package ase;

import java.io.IOException;

public class WeightThreadController {
	Thread thread;
	private static WeightThreadController instance = new WeightThreadController();
	
	private WeightThreadController() {
		
	}
	
	public static WeightThreadController getInstance() { return instance; }
	
	public void newThread(String ip) {
		try {
			thread = new Thread(new WeightController(ip));
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlive() {
		if(thread == null)
			return false;
		
		return thread.isAlive();
	}
}
