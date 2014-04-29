package br.com.senac.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.senac.dao.UsuarioDAO;
import br.com.senac.model.Usuario;

@ManagedBean(name="loginMB")
@RequestScoped
public class LoginMB {

	private Usuario usuario;
	
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
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		if (usuDAO.verificarAcesso(usuario)) {
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
	
	public String sair() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		HttpSession sessao = request.getSession();
		sessao.invalidate();
		return "login";
	}
	
}
