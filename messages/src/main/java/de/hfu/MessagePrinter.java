package de.hfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import de.hfu.messages.domain.model.Message;
import de.hfu.messages.domain.service.MessageService;

@Component
public class MessagePrinter {

	@Autowired
	MessageService messageService;

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void outputMessages(String user) {
		for (Message message : messageService.findAllMessages()) {

			System.out.println(message.getText());

		}
	}

}
