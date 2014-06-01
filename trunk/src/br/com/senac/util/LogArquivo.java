package br.com.senac.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Message;

import br.com.senac.model.Curso;
import br.com.senac.model.Matricula;
import br.com.senac.model.Usuario;

public class LogArquivo {

	private String arquivo = "C:/Temp/loginLog.txt";
	private Date agora = new Date();
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	

	public void gravarLog(Object obj, String mensagem) {

		if (obj instanceof Usuario) {
			Usuario usuario = (Usuario) obj;
			escreverArquivo(
					usuario.getLogin() + " " + mensagem + " "
							+ formato.format(agora), arquivo);
		}
		
		if (obj instanceof Curso) {
			Curso curso = (Curso) obj;
			escreverArquivo(
					curso.getNome() + " " + mensagem + " "
							+ formato.format(agora), arquivo);
		}
		
		if (obj instanceof Matricula) {
			Matricula matricula = (Matricula) obj;
			escreverArquivo(
					matricula.getCodigo() + " " + mensagem + " "
							+ formato.format(agora), arquivo);
		}

	}

	private void escreverArquivo(String texto, String nomeArquivo) {
		try {
			FileWriter writer = new FileWriter(nomeArquivo, true);

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
