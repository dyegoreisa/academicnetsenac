package br.com.senac.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.senac.util.LogArquivo;

public class UsuarioInterceptor {
	
	@AroundInvoke
	public Object loginLog(InvocationContext context) throws Exception {
		
		LogArquivo log = new LogArquivo("/var/log/appJava/academicnetnsenac/usuario.log");
		
		if (context.getMethod().getName().equals("verificarAcesso")) {
			log.gravarLog(context.getParameters()[0], "efetuou login às");
		}
		
		return context.proceed();
	}

}
