package br.com.senac.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_pessoa")
@Table(name="Pessoa")
public abstract class Pessoa {
	
	@Id @GeneratedValue
	private int id;
	private String nome;
	private String sobrenome;
	private String email;
	
	@OneToMany(mappedBy = "pessoa")
	private List<Telefone> telefones;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;

	private String sexo;
	
	public Pessoa(String nome, String sobrenome, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
	}

	public Pessoa(int id, String nome, String sobrenome, String email) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
	}	
	
	public Pessoa(String nome, String sobrenome, String sexo, List<Telefone> telefones,
			Date dataNascimento, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.telefones = telefones;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}

	public Pessoa(int id, String nome, String sobrenome, String sexo,
			List<Telefone> telefones, Date dataNascimento, String email) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.telefones = telefones;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getSexo() {
		return sexo;
	}
	
	public int getSexoInt() {
		if (getSexo() instanceof String && getSexo().equals("F")) {
			return 1;
		}
		return 0;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getDataNascimento(SimpleDateFormat sf) {
		if (dataNascimento != null) {
			return sf.format(dataNascimento);
		}
		return "";
	}	
	
	public java.sql.Date getDataNascimentoToSQL() {
		java.sql.Date data = null;
		try {
			data =  new java.sql.Date(dataNascimento.getTime());
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public void setSexoInt(int sexo) {
		setSexo(sexo == 0 ? "M" : "F");
	}

	public void addTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataNascimento = formatter.parse(dataNascimento);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
