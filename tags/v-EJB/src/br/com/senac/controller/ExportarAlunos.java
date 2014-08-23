package br.com.senac.controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.senac.model.Aluno;

public class ExportarAlunos {
	
	public void exportar(List<Aluno> alunos, String nomeArquivo) {
				
		for (Aluno a : alunos) {
			escreverArquivo(a.getId() + ";"
					+ a.getNome() + ";"
					+ a.getSobrenome() + ";"
					+ a.getSexo() + ";"
					//+ a.getTelefone().toString().trim() + ";"
					+ a.getDataNascimento(new SimpleDateFormat("dd/MM/YYYY")) + ";"
					+ a.getEmail() + ";"
					//+ a.getMatricula() + ";"
					+ a.getBolsa(), 
					nomeArquivo);
		}
				
	}
	
	public void escreverArquivo(String texto, String nomeArquivo) {
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
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
