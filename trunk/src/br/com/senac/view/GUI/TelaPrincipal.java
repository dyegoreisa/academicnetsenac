package br.com.senac.view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 8019909825934557167L;

	public TelaPrincipal() {
		initUI();
	}
	
	private final void initUI() {
		
		JMenuBar menuBar = new JMenuBar();
		System.out.println();
		ImageIcon iconSair = new ImageIcon(getClass().getResource("/images/fechar16x16.png"));
		ImageIcon iconAluno = new ImageIcon(getClass().getResource("/images/aluno16x16.png"));
		ImageIcon iconProfessor = new ImageIcon(getClass().getResource("/images/professor16x16.png"));
		ImageIcon iconTurma = new ImageIcon(getClass().getResource("/images/turma16x16.png"));
		
		JMenu menuCadastro = new JMenu("Cadastro");
		menuCadastro.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem itemMenuAluno = new JMenuItem("Aluno", iconAluno);
		itemMenuAluno.setToolTipText("Gerenciar Aluno");
		itemMenuAluno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenuItem itemMenuProfessor = new JMenuItem("Professor", iconProfessor);
		itemMenuProfessor.setToolTipText("Gerenciar Professor");
		itemMenuProfessor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                TelaListaProfessor tlp = new TelaListaProfessor();
		                tlp.setVisible(true);
		            }
		        });
			}
		});
		
		JMenuItem itemMenuTurma = new JMenuItem("Turma", iconTurma);
		itemMenuTurma.setToolTipText("Gerenciar Turma");
		itemMenuTurma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenu menuSair = new JMenu("Sistema");
		JMenuItem itemMenuSair = new JMenuItem("Sair", iconSair);
		itemMenuSair.setMnemonic(KeyEvent.VK_C);
		itemMenuSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
	            ActionEvent.CTRL_MASK));
		itemMenuSair.setToolTipText("Sair da aplicação");
		itemMenuSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		menuCadastro.add(itemMenuAluno);
		menuCadastro.add(itemMenuProfessor);
		menuCadastro.add(itemMenuTurma);
		
		menuSair.add(itemMenuSair);
		
		menuBar.add(menuCadastro);
		menuBar.add(menuSair);
		
		setJMenuBar(menuBar);
		
		setTitle("Academic Net");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
}
