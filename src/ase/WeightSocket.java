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

	/*
	 *  Method for getting registered
	 *  weight in grams from the scales
	 *  Sending "S" to the scales should make it respond with
     *  S_S_WeightValue_Unit
     *  Usually this will result in "S S 0.0000 KG"
     *  Using a RegExpr we can grab all numbers from the input string
	 */
	@Override
	public int getWeight() throws IOException {
		pw.println("S");
		pw.flush();

		//Grabs a line as a string from the BufferedReader
		
		String in = br.readLine();
		String out = "";
		Pattern regExr = Pattern.compile("\\d+");
		Matcher match = regExr.matcher(in);
		while(match.find())
			out += match.group();
		
		if(out.length() == 5)
			out = out.substring(0, 4);//Second parameter could be replaced with
                                            // a function of the length of the string
		return Integer.parseInt(out);
	}

    /*
     *  Method for setting a new Tare value on the scales
     *  Sending "T" to the scales should make it respond with
     *  T_S_WeightValue_Unit
     *  Usually this will result in "T S 0.0000 KG"
     *  Same procedure as in getWeight(), except the scales
     *  also sets its tare value to the returned value
     */
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
			out = out.substring(0, 4);//Second parameter could be replaced with
                                            // a function of the length of the string
		return Integer.parseInt(out);
	}

	/*
	 * Method for showing "ERROR" in large type on the scales display
	 * The "D" command on the weight is very limited in characters
	 */
	@Override
	public void showError() throws IOException {
		pw.println("D \"ERROR\"");
		pw.flush();
		while(!br.readLine().equals("D A"));
	}

	/*
	 * Method for showing an arbitrary message on the scales display
	 * Less limited in characters than D, but still limited to ~30 characters
	 */
	@Override
	public void showText(String msg) throws IOException {
		pw.println("P111 \"" + msg +"\"");
		pw.flush();
		while(!br.readLine().equals("P111 A"));
	}

	/*
	 * Method for completely clearing the display,
	 * resetting to the default balance display
	 */
	@Override
	public void clearText() throws IOException {
		showText("");
		pw.println("DW");
		pw.flush();
		while(!br.readLine().equals("DW A")); //Ensures the command has been properly understood before continuing
	}

	/*
	 * Utility function for checking if a string can be parsed to an integer
	 * Used before parsing to catch the NumberFormatException and interrupt the parsing
	 */
	boolean tryParseInt(String value) {  
		try {  
			Integer.parseInt(value);  
			return true;  
		} catch (NumberFormatException e) {  
			return false;  
		}  
	}

    /*
     *  Method for getting an integer-formatted input from the user of the scales
     *  Will continue running until a valid integer is entered
     */
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

	/*
	 *  Method for getting a Yes/No answer from the user of the scales
	 *  Is compatible with both the simulator and the real scales
	 */
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

	/*
	 *  Method for halting the program until enter is pressed
	 *  Will set the scales into K 3 mode before, and back to the default K 1 mode after
	 */
	@Override
	public void haltProgress(String msg) throws IOException {
		pw.println("K 3"); //K 3 mode, no function - send code
		pw.flush();

		pw.println("P111 \"" + msg + "\"");
		pw.flush();

		while (!br.readLine().startsWith("K C 4")); //Key function code of the enter button [->
		clearText();

		pw.println("K 1"); //K 1 mode, exec function - no code
		pw.flush();
		
		while(!br.readLine().equals("K A"));
	}

	/*
	 * Method used in testing, only applicable on the simulator - unused in the actual program
	 */
	@Override
	public void overrideWeight(int grams) {
		pw.println("B \""+grams+"\"");
		pw.flush();
	}

    /*
     * Method that should terminate the connection to both the simulator and the actual scales
     */
	@Override
	public void exit() {
		pw.println("Q"); //Simulator, ignored by HW scale
		pw.flush();
		
		pw.println("PWR 1"); //HW scale reset
		pw.flush();
	}

    /*
     * Method used to sleep the program to make the experience smoother
     */
	@Override
	public void sleep(int s) {
		try {
			TimeUnit.SECONDS.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
