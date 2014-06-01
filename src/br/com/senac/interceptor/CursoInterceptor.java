package br.com.senac.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.senac.util.LogArquivo;


public class CursoInterceptor {

	@AroundInvoke
	public Object cadastroLog(InvocationContext context) throws Exception {
		
		LogArquivo log = new LogArquivo();
		
		if (context.getMethod().getName().equals("inserir")) {
			log.gravarLog(context.getParameters()[0], "criado �s");
		}
		
		if (context.getMethod().getName().equals("atualizar")) {
			log.gravarLog(context.getParameters()[0], "atualizado �s");
		}
		
		if (context.getMethod().getName().equals("apagar")) {
			log.gravarLog(context.getParameters()[0], "exclu�do �s");
		}
				
		return context.proceed();
	}
}
