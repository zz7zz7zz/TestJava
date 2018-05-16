import org.apache.log4j.Logger;

public class Log4JMain {
	
	private static Logger logger = Logger.getLogger(Log4JMain.class.getCanonicalName());
	public static void main(String argc[]){
		logger.fatal("fatal: am From Log4JMain");
		logger.error("error: am From Log4JMain");
		logger.warn("warn: am From Log4JMain");
		logger.info("info:I am From Log4JMain");
		logger.debug("debug:I am From Log4JMain");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
