package recording;

/**
 * 
 * Represents an action performed by the robot. 
 *
 */
public class Action {
	
	public String method;
	public Object[] params;
	public double startTime;
	public double endTime;
	private int numParams;
	private double maxParamDiff; //make const?
	private Object[] originalParams;
	
	public Action() {
		
	}
	
	public Action(String methodName, Object[] parameters, double startT, double maxParameterDiff) {
		method = methodName;
		params = parameters;
		originalParams = parameters;
		startTime = startT;
		numParams = 1;
		maxParamDiff = maxParameterDiff;
	}
	
	public Action(String methodName, Object[] parameters, double startT) {
		method = methodName;
		params = parameters;
		originalParams = parameters;
		startTime = startT;
		numParams = 1;
		maxParamDiff = 0;
	}
	
	public boolean Equals(Action a) {
		return a.method.equals(this.method);
	}
	
	/**
	 * 
	 * @param p new parameters of an action currently being executed
	 * @return False if new action must be started, true if parameters are the same or have been averaged
	 * 		   and no new action must be started
	 */
	public boolean equalizeParams(Object[] p) {
		Object[] tempParams = new Object[params.length];
		System.arraycopy(params, 0, tempParams, 0, params.length);
		for (int i = 0; i < params.length; i++) {
			if (p[i] instanceof Integer && tempParams[i] instanceof Integer) {
				if (Math.abs((int)p[i] - (int)originalParams[i]) > maxParamDiff) {
					return false;
				}
				else {
					tempParams[i] = (((int)tempParams[i] * numParams) + (int)p[i]) / (numParams + 1);
				}
			}
			else if (!p[i].equals(tempParams[i])) {
				return false;
			}
		}
		System.arraycopy(tempParams,  0, params,  0, tempParams.length);
		numParams++;
		return true;
	}
}

