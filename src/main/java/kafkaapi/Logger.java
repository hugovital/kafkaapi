package kafkaapi;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static FileWriter init() throws Exception {

		String logDir = System.getProperty("log.dir");
		if (logDir == null || logDir.trim().equals(""))	
			throw new Exception("log.dir não definido");
		
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");		
		
		String fileName = String.format(
					"%s/log_%s.log",
					logDir,
					format.format( new Date() )
				);
		
		FileWriter writer = new FileWriter( fileName, true );

		return writer;

	}
	
	private static void write(FileWriter writer, String msg) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss.SSS");
		String message = String.format( 
				"%s - %s",
				format.format(new Date()),
				msg
				);
		
		writer.write( message );

	}

	public static void log(String msg) throws Exception {

		try(FileWriter writer =  init()){
			write(writer, msg);
		}
		
	}

	public static void log(Exception ex) throws Exception {

		try(FileWriter writer =  init()){
			write(writer, ex.getMessage());
		}
		
	}

}
