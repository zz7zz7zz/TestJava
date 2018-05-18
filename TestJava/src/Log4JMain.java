import org.apache.log4j.Logger;

public class Log4JMain {
	
	//优先读取log4j.xml; 再次读取log4j.properties 
	private static Logger logger = Logger.getLogger(Log4JMain.class.getCanonicalName());
	
	private static Logger errorLog = Logger.getLogger("errorLog");
	
	public static void main(String argc[]){
		
		
		logger.fatal("fatal: am From Log4JMain");
		logger.error("error: am From Log4JMain");
		logger.warn("warn: am From Log4JMain");
		logger.info("info:I am From Log4JMain");
		logger.debug("debug:I am From Log4JMain");
		
		//-------------------------------------
		errorLog.info("--------end--------");
		
		//-------------------------------------
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
