package br.com.lopes.jms;

import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.lopes.configurations.Config;

public class TesteConsumidor {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		Config config = new Config();

		MessageConsumer consumer = config.obterConsumidor();

		// Read only one message
		// Message message = consumer.receive();

		// Read all messages
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;

				try {
					System.out.println("Recebendo mensagem: " + textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		new Scanner(System.in).nextLine();

		config.liberarRecursos();

	}

}
