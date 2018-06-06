package ase;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
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
	}

	@Override
	public void showText(String msg) {
		pw.println("P111 \"" + msg +"\"");
		pw.flush();
	}

	@Override
	public void clearText() {
		pw.println("DW");
		pw.flush();
	}

	@Override
	public String getInput(String msg) {
		String in = "         ";

		pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
		pw.flush();

		while(!in.substring(0,6).equals("RM20 A")){
			try {
				in = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String subStr = in.substring(8,in.length()-1);
		return subStr;
	}

	@Override
	public boolean getConfirmation(String msg) {
		String in = null;

		pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
		pw.flush();

		boolean answerRecieved = false;
		boolean returnValue = false;

		while(!answerRecieved) {
			if(in.contains("RM20 A")) {
				pw.println("RM20 8 \""+msg+"\" \" \" \"&3\"");
				pw.flush();
			}

			try {
				in = br.readLine();
			} catch (IOException e) {}

			if(in.equals("RM20 A \"y\""))
				returnValue = true;

			if(in.equals("RM20 A \"n\""))
				returnValue = false;
		}

		clearText();
		return returnValue;
	}

	@Override
	public void haltProgress(String msg) {
		String in = null;

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


/* //K 3 implementation
    @Override
    public boolean getConfirmation(String msg) {
        try (Socket socket = new Socket( curIP ,8000)) {
            OutputStream sos = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(sos);
            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String in = "         ";

            pw.println(msg);
            pw.flush();
            pw.println("K 3 ");
            pw.flush();

            while(!in.substring(0,5).equals("K C 4")){
                in = reader.readLine();
                //System.out.println(in);
                if(in.equals("CANCEL CASE GOES HERE")){
                    return false;
                }
            }

            pw.println("K 1 ");
            pw.flush();
            pw.println("DW");
            pw.flush();
            return true;
            //socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
 */
@Override
public void overrideWeight(int grams) {
	try (Socket socket = new Socket(curIP, 8000)) {
		OutputStream sos = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(sos);

		pw.println("B \""+grams+"\"");
		pw.flush();

		//socket.close();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public void exit() {
	try (Socket socket = new Socket(curIP, 8000)) {
		OutputStream sos = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(sos);

		pw.println("Q");
		pw.flush();

		//socket.close();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
