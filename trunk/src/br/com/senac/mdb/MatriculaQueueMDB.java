package br.com.senac.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import br.com.senac.model.Matricula;
import br.com.senac.util.LogArquivo;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/matriculaQueue") }, 
		mappedName = "java:/queue/matriculaQueue")
public class MatriculaQueueMDB implements MessageListener {
	
	public MatriculaQueueMDB() {
		
	}
	
	public void onMessage(Message message) {
		try {
 
			if (message instanceof TextMessage) {
				TextMessage mensagem = (TextMessage) message;
				// TODO: fazer alguma coisa aqui!!!
			} else if (message instanceof ObjectMessage) {
				try {
					ObjectMessage obj = (ObjectMessage) message;
					Matricula matricula = (Matricula) obj.getObject();
					LogArquivo log = new LogArquivo("/var/log/appJava/academicnetnsenac/matriculaQueue.log");
					log.gravarLog(matricula, "foi criada e consumida da fila às");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
