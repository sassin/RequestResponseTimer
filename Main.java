import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
	InputStream inputStream;
	public static void main(String[] args) throws IOException
	{
		Main obj = new Main();
		obj.Requester();
		obj.Question();
	}
	
	private void Requester(){
    	
		
			
		 String nOfThreads = readConfig("NumberOfThreads");
		 String nOfRequests=readConfig("NumberOfRequests");
		 String url=readConfig("URL");

		 int workerThreads =StringToInt(nOfThreads);
		 int requestsCount =StringToInt(nOfRequests);

		 ExecutorService executor = Executors.newFixedThreadPool(workerThreads);
		 int reqCounter = 0;
		 while(reqCounter < requestsCount)
		 {
			 Runnable worker = new HeartBeat(reqCounter, url);
			 executor.execute(worker);
			 reqCounter++;
		 }
		 	executor.shutdown();
		 	while (!executor.isTerminated()) {
		 	}
		}
	
	private String readConfig(String key)
	{
		String value="";
		Properties config = new Properties();
		String configFileName = "config.properties";
		 
		inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
		 
		 try
	  	 {
			if (inputStream != null) {
				config.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + configFileName + "' not found in the classpath");
			}
			value = config.getProperty(key);
		 }catch(Exception e){
			System.out.println("cant find the property");
		 }
		 
		 return value;
	}
	
	
	private int StringToInt(String str)
	{
		int val =0;
		try{
			val = Integer.valueOf(str);
		}catch(Exception e){
			System.out.println("cant convert string to int returning 0 instead");
		 }
		
		return val;
	}
	
	private void Question()
	{
		//TimeKeeper.getInstance().printAllTime();
	 	System.out.println(" 10 percentile "+TimeKeeper.getInstance().percentiler(10));
	 	System.out.println(" 50 percentile "+TimeKeeper.getInstance().percentiler(50));
	 	System.out.println(" 90 percentile "+TimeKeeper.getInstance().percentiler(90));
	 	System.out.println(" 95 percentile "+ TimeKeeper.getInstance().percentiler(95));
	 	System.out.println(" 99 percentile "+TimeKeeper.getInstance().percentiler(99));
	 	System.out.println(" Mean "+TimeKeeper.getInstance().meanFunction());
	 	System.out.println(" Standard Deviation "+TimeKeeper.getInstance().sdFunction());
	}

}