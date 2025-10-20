package ca.nbfg.rbo.config;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LoggingBncJulTo4J extends Handler{

	public static Logger logger = LoggerFactory.getLogger(LoggingBncJulTo4J.class);


	@Override
	public void publish(LogRecord record) {
		logger.debug(record.getMessage());		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub
		
	}
	

}
