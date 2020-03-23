package com.report.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class PrintSourceBean {

	private Source source;
	
	@Autowired
	public PrintSourceBean(Source source) {
		this.source=source;
	}
	
	public void publishSimplePrintMessage(String message) {
		source.output().send(MessageBuilder.withPayload(message)
				.build());
	}
}
