package ase;

import java.io.IOException;

public class WeightThread {
	Thread thread;
	private static WeightThread instance = new WeightThread();
	
	private WeightThread() {
		
	}
	
	public static WeightThread getInstance() { return instance; }
	
	public void newThread(String ip) {
		try {
			thread = new Thread(new WeightController(ip));
			thread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isAlive() {
		if(thread == null)
			return false;
		
		return thread.isAlive();
	}
}
