package recording;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;

/**
 * 
 * run on the robot itself; stores all the actions being performed by the robot; sends to driverStation when ready
 *
 */
public class Recorder {
	
	public ArrayList<Action> actions;
	public ArrayList<Action> current;
	public ArrayList<String> currentIter;
	
	private final String hostName = "";
	private final int port = 80;
	
	//Timer t; //t only apart of FIRST library
	boolean on;
	
	public Recorder(boolean b) {
		on = b;
		actions = new ArrayList<Action>();
		current = new ArrayList<Action>();
		currentIter = new ArrayList<String>();
	}
	
	public void add(Action a) {
		if (on) {
			Action acts = current.get(0);
			for (int i = 0; i < current.size(); i++, acts = current.get(i)) {
				 if (a.Equals(acts)) {
					 if (acts.equalizeParams(a.params)) {
						 return;
					 }
					 else {
						 break;
					 }
				 }
			}
			a.startTime = getTime();
			current.add(a);
			currentIter.add(a.method);
		}
	}
	
	private void push(Action a) {
		if (on) {
			a.endTime = getTime();
			actions.add(a);
			if (current.contains(a)) {
				current.remove(a);
			}
			if (currentIter.contains(a)) {
				currentIter.remove(a);
			}
		}
	}
	
	public void clearIter() {
		if (on) {
			Action acts = actions.get(0);
			for (int i = 0; i < actions.size(); i++, acts = actions.get(i)) {
				if (!currentIter.contains(acts)) {
					push(acts);
					i--;
				}
			}
			currentIter.clear();
		}
	}
	
	public double getTime() {
		//return t.get();
		return 1.000;
	}
	
	public void start() {
//		t.reset();
//		t.start();
		on = true;
	}
	
	public void send() throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getByName(hostName), port);
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		for (int i = 0; i < actions.size(); i++) {
			Action acts = actions.get(i);
			out.println(acts.method);
			for (int j = 0; j < acts.params.length; j++) {
				out.println(acts.params[j]);
			}
			out.println("endOfParams");
			out.println(acts.startTime);
			out.println(acts.endTime);
		}
		out.println("endOfActions");
		socket.close();
		actions.clear();
		current.clear();
		currentIter.clear();
	}
}
