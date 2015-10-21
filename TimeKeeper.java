import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;


public class TimeKeeper {
	
	 Vector<Long> responseTime = new Vector<>();
	 
	 boolean isSorted = false;
	 long counter=0;
	 long timeSum =0;
	 private static TimeKeeper TimeKeeperInstance = null;

	 public static TimeKeeper getInstance()
	 {
		 if(TimeKeeperInstance == null) {
			 TimeKeeperInstance = new TimeKeeper();
	      }
	      return TimeKeeperInstance;
	 }
	 
	 public synchronized void addTime(long time)
	 {
		 timeSum += time;
		 responseTime.add(time);
		 counter++;
		 isSorted = false;
	 }
	 
	 public void printAllTime()
	 {
		 Iterator<Long> itr = responseTime.iterator();
		 while(itr.hasNext())
		 {
			 System.out.println(itr.next());
		 }
	 }
	 
	 public long percentiler(int n)
	 {
		 int adjustment = (int)(counter > 0?1:0);
		 if(!isSorted)
		 {
			 Collections.sort(responseTime);
			 isSorted = true;
		 }
		 System.out.println("---------------------------------------------------------");
		 int rawIndex = (int)(Math.round((n/100.0)*counter)) - adjustment;
		 
		 return responseTime.get(rawIndex);
	 }
	 
	 public long meanFunction()
	 {
		 System.out.println("---------------------------------------------------------");
		 if(counter != 0)
			 return timeSum/counter;
		 return 0L;
	 }
	 
	 public long sdFunction()
	 {
		 long mean = TimeKeeper.getInstance().meanFunction();
		 long responseTimeSquared = 0L;
		 for(int i =0; i < counter; i++)
		 {
			long diff = responseTime.get(i) - mean;
			responseTimeSquared += diff*diff;
		 }
		 
		 
		 if(counter != 0)
		 {
			 
			 double variance = (responseTimeSquared/counter);
			 return (long)Math.sqrt(variance);
		 }
			 
		 return 0L;
	 }
}
