package br.com.lopes.jms.topic;

import java.io.StringWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.xml.bind.JAXB;

import br.com.lopes.jms.modelo.Pedido;
import br.com.lopes.jms.modelo.PedidoFactory;

public class TestaProdutorTopico {

	public static void main(String[] args) throws Exception {

		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

		Connection connection = factory.createConnection("user", "user");
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination topico = (Destination) context.lookup("loja");
		MessageProducer producer = session.createProducer(topico);

		// Gera xml a partir de objeto
		Pedido pedido = new PedidoFactory().geraPedidoComValores();
		StringWriter writer = new StringWriter();
		JAXB.marshal(pedido, writer);

		// Gera string a partir de xml
		String xml = writer.toString();
		System.out.println(xml);

		Message message = session.createTextMessage(xml);
		// message.setBooleanProperty("ebook", true);
		producer.send(message);

		session.close();
		connection.close();
		context.close();

	}

}
