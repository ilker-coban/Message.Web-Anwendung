package de.hfu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import de.hfu.messages.domain.model.Message;
import de.hfu.messages.domain.service.MessageService;

@WebServlet("/servlet.html")
public class MessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());

		MessageService messageService = (MessageService) applicationContext
				.getBean("messageService");
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter(); 
		out.append("<!DOCTYPE html><html><body><ul>");
		
		for(Message iter : messageService.findAllMessages() ){
			
			out.append("<li>" +	iter.getText() + "</li>");	
		}
		
		out.append("</ul> </body> </html>");

		

	}

}
