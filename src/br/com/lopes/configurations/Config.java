package br.com.lopes.configurations;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Config {

	private static InitialContext context;
	private static Connection connection;
	private static Session session;
	private static Destination destination;

	public Config() {
		obterSession();
	}

	public MessageConsumer obterConsumidor() throws Exception {
		return session.createConsumer(destination);
	}

	public MessageProducer obterProdutor() throws Exception {
		return session.createProducer(destination);
	}

	public Message obterMessage(String mensagem) throws Exception {
		return session.createTextMessage(mensagem);
	}

	public void liberarRecursos() {
		try {
			session.close();
			connection.close();
			context.close();
		} catch (JMSException | NamingException e) {
			e.printStackTrace();
		}
	}

	private void obterSession() {
		try {
			context = new InitialContext(obterConfiguracoes());
			ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = (Destination) context.lookup("financeiro");
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}

	}

	private Properties obterConfiguracoes() {
		Properties properties = new Properties();
		properties.setProperty("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.setProperty("java.naming.provider.url", "tcp://localhost:61616");
		properties.setProperty("queue.financeiro", "fila.financeiro");
		return properties;
	}

}
