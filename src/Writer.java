import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.apache.commons.lang3.math.NumberUtils;

public class Writer {
	
	private static final int port = 80;
	private static final String actionCodeTemplate = 
			"if ((ti = t.get()) > a.startTime && ti < a.endTime) {\n"
			+ "\n\t a.method;\n" //a.method must be modified to include parameters in a.params[0], a.params[1]...
		  + "}\n";
	private static final String beginAutonCodeTemplate = "public void auton() { \n";
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		
		ServerSocket server = new ServerSocket(80);
		Socket socket;
		
		socket = server.accept();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String ln = in.readLine();
		while (ln != "endOfActions") {
			Action acts = new Action();
			acts.method = ln;
			ArrayList<Object> temp = new ArrayList<Object>();
			ln = in.readLine();
			while (ln != "endOfParams") {
				String param = in.readLine();
				if (NumberUtils.isNumber(ln)) {
					temp.add(Double.parseDouble(ln));
				}
				else if (ln.equals("true")) {
					temp.add(true);
				}
				else if (ln.equals("false")) {
					temp.add(false);
				}
				else {
					temp.add(ln);
				}
			}
			acts.params = temp.toArray();
			acts.startTime = Double.parseDouble(in.readLine());
			acts.endTime = Double.parseDouble(in.readLine());
			actions.add(acts);
			ln = in.readLine();
		}
		in.close();
		server.close();
		socket.close();
		
		
		String code = write(actions);
	}
	
	public static String write(ArrayList<Action> actions) {
		
		String code = beginAutonCodeTemplate;
		
		for (Action a : actions) {
			String ifStatement = "";
			String[] templateSplit = actionCodeTemplate.split("a.startTime");
			ifStatement = templateSplit[0] + a.startTime + templateSplit[1];
			templateSplit = ifStatement.split("a.endTime");
			ifStatement = templateSplit[0] + a.endTime + templateSplit[1];
			String methodCall = createActionMethod(a);
			templateSplit = ifStatement.split("a.method");
			ifStatement = templateSplit[0] + methodCall + templateSplit[1];
			code += ifStatement;
		}
		code += "\n}";
		return code;
	}
	
	public static String createActionMethod(Action a) {
		String methodCall = a.method;
		
		return methodCall;
	}
}