// singleton design pattern
package application;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.OutputStreamWriter;

public class Logger {
	private static Logger instance;
	private static PrintWriter out;
	
	private Logger()
	{
		try {
            out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
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
