// singleton design pattern
package application;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Logger {
	private static Logger instance;
	private static PrintWriter out;
	
	static {
		try {
			out = new PrintWriter(new FileOutputStream("logs/log.txt"));
		} catch (FileNotFoundException e) {
			// drop the exception, we just won't have a logger
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
