package kafkaapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static kafkaapi.Logger.log;

public class ExecutorProcessos {
	
    public String execute(String command) throws Exception {

    	StringBuilder sb = new StringBuilder();

        ProcessBuilder processBuilder = new ProcessBuilder();
        
        // Windows
        //processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");
        
        processBuilder.command( command );

        try {

            Process process = processBuilder.start();

            try ( BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())) ){
            	
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line);
                    sb.append("\r\n");
                }

                int exitCode = process.waitFor();

                String exited = "\nExited with error code : " + exitCode;
                System.out.println( exited );
                sb.append("\r\n");
                sb.append(exited);
                sb.append("\r\n");

            }

        } catch (IOException e) {
        	
            e.printStackTrace();
            log(e);
            sb.append( e.getMessage() );
            
        } catch (InterruptedException e) {
        	
            e.printStackTrace();
            log(e);
            sb.append( e.getMessage() );
            
        }
        
        return sb.toString();

    }

}
