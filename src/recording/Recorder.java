package recording;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import org.apache.commons.lang3.time.StopWatch;
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

	StopWatch watch = new StopWatch();
	boolean on;
	
	public Recorder(boolean b) {
		on = b;
		actions = new ArrayList<Action>();
		current = new ArrayList<Action>();
		currentIter = new ArrayList<String>();
	}
	
	public void add(Action a) {
		if (on) {
			if (actions.size() > 0) {
				for (int i = 0; i < current.size(); i++) {
					Action acts = current.get(i);
					 if (a.Equals(acts)) {
						 if (acts.equalizeParams(a.params)) {
							 return;
						 }
						 else {
							 break;
						 }
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
			if (current.size() > 0) {
				for (int i = 0; i < current.size(); i++) {
					Action acts = current.get(i);
					if (!currentIter.contains(acts)) {
						push(acts);
						//i--;
					}
				}
				currentIter.clear();
			}
		}
	}
	
	public double getTime() {
		return watch.getNanoTime();
	}
	
	public void start() {
		watch.reset();
		watch.start();
		on = true;
	}
	
	public void stop() {
		while (current.size() > 0){
			push(current.get(current.size() - 1));
		}
		currentIter.clear();
		watch.stop();
		watch.reset();
		on = false;
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
