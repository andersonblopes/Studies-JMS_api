package br.com.lopes.jms.fila;

import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;

import br.com.lopes.jms.configuration.Config;

public class TesteProdutorFila {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Config config = new Config();

		MessageProducer producer = config.obterProdutor();

		String msg = "<pedido><id>" + "INFO" + "</id></pedido>";
		/*
		Parâmetros:
		1º: Mensagem
		1º: Guarda ou não no banco de dados em caso do servidor(MON) cair
		1º: Prioridade da Mensagem - 0 a 9 - Configuração necessária no 'activemq.xml'
		4º: Tempo de vida da Mensagem
		*/
		producer.send(config.obterMessage(msg), DeliveryMode.PERSISTENT, 3, 50000);
		/*
		 * for (int i = 0; i < 1000; i++) { String msg = "<pedido><id>" + i +
		 * "</id></pedido>"; producer.send(config.obterMessage(msg)); }
		 */

		// new Scanner(System.in).nextLine();

		config.liberarRecursos();
	}

}
