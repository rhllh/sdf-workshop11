package com.vttp2022.sdfworkshop11;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SdfWorkshop11Application {
	private static final Logger logger = LoggerFactory.getLogger(SdfWorkshop11Application.class);
	private static final String DEFAULT_PORT = "3000";

	@Bean
	public CommonsRequestLoggingFilter requestLoggerFilter() {
		CommonsRequestLoggingFilter requestLogger = new CommonsRequestLoggingFilter();
		requestLogger.setIncludeClientInfo(true);
		requestLogger.setIncludeHeaders(true);
		requestLogger.setIncludeQueryString(true);
		requestLogger.setIncludePayload(true);
		return requestLogger;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SdfWorkshop11Application.class);
		String isDebugEnabled = String.valueOf(logger.isDebugEnabled());
		logger.info(isDebugEnabled);
		logger.debug("Web app");

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsVal = appArgs.getOptionValues("port");
		logger.info("opsVal > " + opsVal);

		String port = null;
		if (opsVal == null || opsVal.get(0) == null) {
			port = System.getenv("PORT");
			if (port == null) {
				port = DEFAULT_PORT;
			}
		} else {
			port = String.valueOf(opsVal.get(0));
		}

		if (port != null) {
			app.setDefaultProperties(Collections.singletonMap("server.port", port));
		}

		app.run(args);
	}

}
