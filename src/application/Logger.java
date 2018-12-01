// singleton design pattern
package application;

public class Logger {
	private static Logger instance;
	
	private Logger()
	{
		
	}
	
	public static synchronized Logger getInstance()
	{
		if (instance == null)
			instance = new Logger();
		return instance;
	}
	
	public void Log(String string) {
		System.out.println(string);
	}
}
