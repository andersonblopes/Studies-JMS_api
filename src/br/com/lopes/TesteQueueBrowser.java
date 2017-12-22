package br.com.lopes;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import br.com.lopes.configurations.Config;

public class TesteQueueBrowser {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws JMSException {
		Config config = new Config();

		Enumeration msgs = config.obterQueueBrowser().getEnumeration();
		while (msgs.hasMoreElements()) {
			TextMessage msg = (TextMessage) msgs.nextElement();
			System.out.println("Message in Queue: " + msg.getText());
		}
	}

}
