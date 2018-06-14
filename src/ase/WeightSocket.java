package ase;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeightSocket implements IWeightSocket{
	String curIP;
	Socket socket;
	OutputStream os;
	PrintWriter pw;
	InputStream is;
	BufferedReader br;

	public WeightSocket(String ip) throws UnknownHostException, IOException{
		curIP = ip;
		socket = new Socket(curIP, 8000);
		os = socket.getOutputStream();
		pw = new PrintWriter(os);
		is = socket.getInputStream();
		br = new BufferedReader(new InputStreamReader(is));
	}

	@Override
	public int getWeight() throws IOException {
		pw.println("S");
		pw.flush();

		//Get input
		String in = br.readLine();
		String out = "";
		Pattern regExr = Pattern.compile("\\d+");
		Matcher match = regExr.matcher(in);
		while(match.find())
			out += match.group();
		
		if(out.length() == 5)
			out = out.substring(0, 4); //in.length - 1

		//Return input
		return Integer.parseInt(out);
	}

	@Override
	public int tare() throws IOException {
		pw.println("T");
		pw.flush();

		String in = br.readLine();
		String out = "";

		Pattern regExr = Pattern.compile("\\d+");
		Matcher match = regExr.matcher(in);
		while(match.find())
			out += match.group();
		
		if(out.length() == 5)
			out = out.substring(0, 4);
		
		return Integer.parseInt(out);
	}

	@Override
	public void showError() throws IOException {
		pw.println("D \"ERROR\"");
		pw.flush();
		while(!br.readLine().equals("D A"));
	}

	@Override
	public void showText(String msg) throws IOException {
		pw.println("P111 \"" + msg +"\"");
		pw.flush();
		while(!br.readLine().equals("P111 A"));
	}

	@Override
	public void clearText() throws IOException {
		showText("");
		pw.println("DW");
		pw.flush();
		while(!br.readLine().equals("DW A"));
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
	public int getInput(String msg) throws IOException {
		String in = "";
		int tmp = -1;
		boolean inputRecieved = false;

		pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
		pw.flush();

		while(!inputRecieved){
			in = br.readLine();
			if(in.startsWith("RM20 A")) {
				in = in.substring(in.indexOf("\"")+1,in.lastIndexOf("\""));
				if(tryParseInt(in)) {
					tmp = Integer.parseInt(in);
					inputRecieved = true;
				} else {
					showText("Fejl - Kun tal tilladt");
					sleep(3);
					clearText();
					pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
					pw.flush();
				}
			} else if (in.startsWith("RM20 C")) {
				clearText();
				pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
				pw.flush();
			}
		}
		return tmp;
	}

	@Override
	public boolean getConfirmation(String msg) throws IOException {
		String in = "";

		pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
		pw.flush();

		boolean answerRecieved = false;
		boolean returnValue = false;

		while(!answerRecieved) {
			in = br.readLine();
			if(in.equals("RM20 A \"y\"")){
				returnValue = true;
				answerRecieved = true;
			} else if(in.equals("RM20 A \"n\"")) {
				returnValue = false;
				answerRecieved = true;
			} else if (in.startsWith("RM20 A")) {
				returnValue = true;
				answerRecieved = true;
			} else if (in.startsWith("RM20 C")) {
				returnValue = false;
				answerRecieved = true;
			}
		}

		clearText();
		return returnValue;
	}

	@Override
	public void haltProgress(String msg) throws IOException {
		pw.println("K 3"); //K 3 mode, no function - send code
		pw.flush();

		pw.println("P111 \"" + msg + "\"");
		pw.flush();

		while (br.readLine().startsWith("K C 4"));
		clearText();

		pw.println("K 1"); //K 1 mode, exec function - no code
		pw.flush();
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
			TimeUnit.SECONDS.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
