package defaultPackage;

import javax.ws.rs.WebApplicationException;

public class runnableThread implements Runnable {
	
		@Override
		public void run() {
			try {
				WeightController WC = new WeightController();
				WC.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
}
