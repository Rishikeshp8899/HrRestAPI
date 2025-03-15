package com.mindgate.main.configuration;

import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class LoggingService {
	   private static final Logger logger = LogManager.getLogger(LoggingService.class);

	    public void performTask() {
	        logger.debug("Debug log message");
	        logger.info("Info log message");
	        logger.warn("Warning log message");
	        logger.error("Error log message");
	    }
}
