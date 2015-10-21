Assumption: user has java setup on his machine. The environment variables for Java are set. JDK path is on the system path.

Steps to run:
1. Save the RequestAnalyzer.zip folder on your local machine.
2. Unzip the zip folder using 7Zip or any other compatible tool.
3. Run command prompt / terminal
4. Change directory into the unzipped folder
5 Type -> javac *.java
6 Type -> java Main

############   you should be able to see the required stats  ##################


To change the config:
1. Go to the unzipped folder.
2. Open config.property. It is in "key=value" format 
3. Change the value of key, "NumberOfThreads" to the desired value as the number of threads to be spawned. Default value = 10
4. Change the value of key, "NumberOfRequests" to the desired value as the number of requests to be made. Default value = 100
5. Change the value of key, "URL" to the desired value as the url to which we want to analyze. Default value = http://en.wikipedia.org/wiki/Main_Page
