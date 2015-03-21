import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.apache.commons.lang3.math.NumberUtils;

public class Writer {
	private final int port = 80;
	private ArrayList<Action> actions;
	
	public static void main(String[] args) throws IOException {
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
					temp.add(Integer.parseInt(ln));
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
			ln = in.readLine();
		}
		server.close();
		socket.close();
	}
	
	
}