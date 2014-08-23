package br.com.senac.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;

import br.com.senac.model.Curso;
import br.com.senac.model.Matricula;
import br.com.senac.model.Usuario;

public class LogArquivo {

	private String arquivo;
	private Date agora = new Date();
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public LogArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	public void gravarLog(Object obj, String mensagem) {
		
		if (obj instanceof String) {
			String texto = (String) obj;
			escreverArquivo(formato.format(agora) + " - " + mensagem + " - " + texto);
		}
		
		if (obj instanceof Usuario) {
			Usuario usuario = (Usuario) obj;
			escreverArquivo(formato.format(agora) + " - " + mensagem + " - " + usuario.getLogin());
		}
		
		if (obj instanceof Curso) {
			Curso curso = (Curso) obj;
			escreverArquivo(formato.format(agora) + " - " + mensagem + " - " + curso.getNome());
		}
		
		if (obj instanceof Matricula) {
			Matricula matricula = (Matricula) obj;
			escreverArquivo(formato.format(agora) + " - " + mensagem + " - " + matricula.getCodigo());
		}

	}

	private void escreverArquivo(String texto) {

		if (arquivo == null) {
			throw new NullPointerException();
		}
		
		if ("".equals(arquivo)) {
			throw new EmptyStackException();
		}
		
		try {
			FileWriter writer = new FileWriter(arquivo, true);

			BufferedWriter bw = new BufferedWriter(writer);

			bw.append(texto);
			bw.newLine();

			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
