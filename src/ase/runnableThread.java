package ase;

import ase.WeightController;

public class runnableThread implements Runnable {
	
		@Override
		public void run() {
			WeightController WC = new WeightController();
			WC.run();
		}
}
