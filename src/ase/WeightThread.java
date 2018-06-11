package ase;

public class WeightThread {
	Thread thread;
	private static WeightThread instance = new WeightThread();
	
	private WeightThread() {
		
	}
	
	public static WeightThread getInstance() { return instance; }
	
	public void newThread() {
		thread = new Thread(new WeightController());
		thread.start();
	}
	
	public boolean isAlive() {
		if(thread == null)
			return false;
		
		return thread.isAlive();
	}
}
