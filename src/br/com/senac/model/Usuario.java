package br.com.senac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({ 
	@NamedQuery(
			name = "verificarLoginSenhaUsuario", 
			query = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha "
	) 
})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1529079161025490541L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;

	private String nome;
	
	@Column(unique = true)
	private String login;
	private String senha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario usu = (Usuario) obj;
			if (this.login.equals(usu.getLogin())) {
				if (this.senha.equals(usu.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}

}
