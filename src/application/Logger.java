// singleton design pattern
package application;

public class Logger {
	private static Logger instance;	
	
	public static synchronized Logger getInstance()
	{
		if (instance == null)
			instance = new Logger();
		return instance;
	}
	
	public void log(String string) {
		// this is non functional. sonarcloud does not support files since it leaves a security hole	
	}

	
}
