package br.com.lopes.jms;

import javax.jms.MessageProducer;

import br.com.lopes.configurations.Config;

public class TesteProdutor {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Config config = new Config();

		MessageProducer producer = config.obterProdutor();

		for (int i = 0; i < 1000; i++) {
			String msg = "<pedido><id>" + i + "</id></pedido>";
			producer.send(config.obterMessage(msg));
		}

		// new Scanner(System.in).nextLine();

		config.liberarRecursos();
	}

}
