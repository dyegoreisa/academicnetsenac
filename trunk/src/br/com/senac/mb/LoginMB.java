package br.com.senac.mb;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.senac.dao.UsuarioDAO;
import br.com.senac.model.Usuario;

@ManagedBean(name="loginMB")
@RequestScoped
public class LoginMB {

	private Usuario usuario;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	public LoginMB() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logar() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        HttpSession sessao = request.getSession();  
		
		String destino = "";
		
		if (usuarioDAO.verificarAcesso(usuario)) {
			sessao.setAttribute("usuarioLogado", usuario);
			destino = "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Mensagem: ",
							"Login ou senha inválidos!"));			
			sessao.invalidate();
			destino = "login";
		}
		return destino;
	}
	
	public void sair() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/Login.jsf");
	}
}