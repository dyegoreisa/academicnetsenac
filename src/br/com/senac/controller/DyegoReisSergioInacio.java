package br.com.senac.controller;

import javax.swing.SwingUtilities;

import br.com.senac.view.TelaPrincipal;

public class DyegoReisSergioInacio {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaPrincipal tp = new TelaPrincipal();
                tp.setVisible(true);
            }
        });
	}
}
