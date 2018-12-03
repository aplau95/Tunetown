// singleton design pattern
package application;

import java.io.*;

public class Logger {
	private static Logger instance;
	private static PrintWriter out;
	
	static {
		try {
			File logFile = new File("logs/log.txt");
			logFile.getParentFile().mkdirs();
			logFile.createNewFile(); // if file already exists will do nothing
			out = new PrintWriter(new FileOutputStream(logFile));
		} catch (IOException e) {
			// drop the exception, we just won't have a logger
			e.printStackTrace();
		}
	}
	
	private Logger()
	{
	}
	
	public static synchronized Logger getInstance()
	{
		if (instance == null)
			instance = new Logger();
		return instance;
	}
	
	public void log(String string) {
		out.println(string);
	}
}
