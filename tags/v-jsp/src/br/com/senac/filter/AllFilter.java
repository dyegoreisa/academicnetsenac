package br.com.senac.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.senac.model.Usuario;

/**
 * Servlet Filter implementation class MeuFilter
 */
@WebFilter("/*")
public class AllFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AllFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
//		System.out.println("ServletPath: " + httpRequest.getServletPath());
//		System.out.println("RequestURI: " + httpRequest.getRequestURI());
		
		HttpSession sessao = httpRequest.getSession();
	
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		if (httpRequest.getRequestURI().contains("bootstrap") ||
				httpRequest.getRequestURI().contains("LoginServlet")) {
//			System.out.println("Liberado!!");
			chain.doFilter(request, response);
		} else if (usuario == null) {
//			System.out.println("Não está logado!");
			sessao.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} else {
//			System.out.println(usuario.getLogin() + " Logado!");
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
