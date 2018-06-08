package ase;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class WeightSocket implements IWeightSocket{
	String curIP;

	Socket socket;
	OutputStream os;
	PrintWriter pw;
	InputStream is;
	BufferedReader br;

	public WeightSocket(){
		curIP = "localhost"; //TODO make ip dynamic
		try {
			socket = new Socket(curIP, 8000);
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getWeight() {
		//Hvad betyder det her?
		pw.println("S");
		pw.flush();

		//Get input
		String in;
		try {
			in = br.readLine();
			String subStr = in.substring(in.length()-7,in.length()-2);
			String subStr2 = subStr.charAt(0) + subStr.substring(2);
			in = subStr2;
		} catch (IOException e) {
			in = "0";
		}

		//Return input
		return Integer.parseInt(in);
	}

	@Override
	public int tare() {
		pw.println("T");
		pw.flush();
		String in;
		try {
			in = br.readLine();
			String subStr = in.substring(in.length()-7,in.length()-2);
			String subStr2 = subStr.charAt(0) + subStr.substring(2);
			in = subStr2;
		} catch (IOException e) {
			in = "0";
		}
		return Integer.parseInt(in);
	}

	@Override
	public void showError() {
		pw.println("D \"ERROR\"");
		pw.flush();
		try {
			while(!br.readLine().equals("D A"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showText(String msg) {
		pw.println("P111 \"" + msg +"\"");
		pw.flush();
		try {
			while(!br.readLine().equals("P111 A"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clearText() {
		pw.println("DW");
		pw.flush();
		try {
			while(!br.readLine().equals("DW A"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}

	@Override
	public int getInput(String msg) {
		String in = "";
		int tmp = -1;

		pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
		pw.flush();

		while(!in.startsWith("RM20 A")){
			try {
				in = br.readLine();
				in = in.substring(8,in.length()-1);
				if(tryParseInt(in)) {
					tmp = Integer.parseInt(in);
				} else {
					showText("Fejl - Kun tal tilladt");
					sleep(3);
					clearText();
                    pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
                    pw.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tmp;
	}

	@Override
	public boolean getConfirmation(String msg) {
		String in = "";

		pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
		pw.flush();

		boolean answerRecieved = false;
		boolean returnValue = false;

		while(!answerRecieved) {

			try {
				in = br.readLine();
			} catch (IOException e) {}

			if(in.equals("RM20 A \"y\"")){
				returnValue = true;
				answerRecieved = true;
			} else if(in.equals("RM20 A \"n\"")) {
				returnValue = false;
				answerRecieved = true;
			} else if (in.startsWith("RM20 A")) {
				pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
				pw.flush();
			}
		}

		clearText();
		return returnValue;
	}

	@Override
	public void haltProgress(String msg) {
		String in = "";

		pw.println("P111 \"" + msg + "\"");
		pw.flush();

		while (!in.startsWith("K A")) {
			try {
				in = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		clearText();
	}

	@Override
	public void overrideWeight(int grams) {
		pw.println("B \""+grams+"\"");
		pw.flush();
	}

	@Override
	public void exit() {
		pw.println("Q");
		pw.flush();
	}

	@Override
	public void sleep(int s) {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
