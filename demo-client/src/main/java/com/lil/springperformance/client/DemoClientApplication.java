package com.lil.springperformance.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lil.springperformance.client.domain.CpuLoader;
import com.lil.springperformance.client.domain.Quote;
import com.lil.springperformance.client.domain.DemoProperties;
import com.lil.springperformance.client.manage.DemoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoClientApplication {

	private static Logger logger = LoggerFactory.getLogger(DemoClientApplication.class);

	private static DemoManager demoManager;

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml",
				DemoClientApplication.class);
		DemoProperties props = (DemoProperties) context.getBean("appProperties");
		SpringApplication demoApplication = new SpringApplication(DemoClientApplication.class);
		// Below lines require JVM parameters in order to run. This is covered in lesson
		// 03_03.
		BufferingApplicationStartup bas = new BufferingApplicationStartup(10000);
		bas.addFilter(startupStep -> startupStep.getName().startsWith("spring.boot"));
		
		demoApplication.setApplicationStartup(bas);		
		demoApplication.run(args);
		logger.info("Open this application in your browser at http://localhost:"
				+ props.getRuntimeProperties().getProperty("server.port", ""));
		demoManager = new DemoManager(props);
		context.close();
	}

	@Bean
	public CpuLoader tracer() {
		return new CpuLoader();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {			
			String response = restTemplate.getForObject("https://api.quotable.io/quotes/random", String.class);			
	        ObjectMapper mapper = new ObjectMapper();	        
	        Quote[] quotes = mapper.readValue(response, Quote[].class);
	        Quote quote = quotes[0]; // get the first element
		};
	}

}
