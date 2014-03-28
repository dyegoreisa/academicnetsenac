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
@WebFilter("/paginas/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		
		HttpSession sessao = httpRequest.getSession();
	
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		System.out.println("O usuário é: " + usuario);

		if (usuario == null) {
			System.out.println("Não está logado!");
			request.setAttribute("mensagem", "Login ou senha inválidos!");
			sessao.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("../login.jsp");
			rd.forward(request, response);
		} else {
			System.out.println(usuario.getLogin() + " Logado!");
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
