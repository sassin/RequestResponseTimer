import java.net.*;
import java.io.*;

public class HeartBeat implements Runnable {
	int seq;
	String urlLink;
	 public HeartBeat(int s, String link)
	 {
		seq =s;
		urlLink = link;
	 }
	 
	@Override
	public void run(){
		processCommand();
	}
	
	private void processCommand() {
		 URL url;
		 long diff=0;
			try {
				
				url = new URL(urlLink);
		        URLConnection urlConnection;
		        long start = System.currentTimeMillis();
		        urlConnection = url.openConnection();
		        BufferedReader in = new BufferedReader( 
		        							new InputStreamReader( urlConnection.getInputStream()));
			    long end = System.currentTimeMillis();
		        diff = end-start;
		        in.close();
		        TimeKeeper.getInstance().addTime(diff);
			}
			catch (IOException e) {
				System.out.println("Failed to receive response from the url");
			}
	}

}
