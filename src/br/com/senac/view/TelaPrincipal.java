package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 8019909825934557167L;

	private ImageIcon favicon;
	private ImageIcon logo;
	private ImageIcon iconSair;
	private ImageIcon iconAluno;
	private ImageIcon iconProfessor;
	private ImageIcon iconTurma;
	private ImageIcon iconDisciplina;
	private ImageIcon iconCurso;	

	public TelaPrincipal() {
		initUI();
	}
	
	private final void initUI() {
		
		// Carregando as imagens
		logo = new ImageIcon(getClass().getResource("/images/academicnet_logo_texto.png"));
		favicon = new ImageIcon(getClass().getResource("/images/academicnet_logo16x16.png"));
		iconSair = new ImageIcon(getClass().getResource("/images/fechar16x16.png"));
		iconAluno = new ImageIcon(getClass().getResource("/images/aluno16x16.png"));
		iconProfessor = new ImageIcon(getClass().getResource("/images/professor16x16.png"));
		iconTurma = new ImageIcon(getClass().getResource("/images/turma16x16.png"));
		iconDisciplina = new ImageIcon(getClass().getResource("/images/disciplina16x16.png"));
		iconCurso = new ImageIcon(getClass().getResource("/images/curso16x16.png"));

		// Criando os menus
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCadastro = new JMenu("Cadastro");
		JMenu menuGestao = new JMenu("Gestão Academica");
		JMenu menuRelatorio = new JMenu("Relatorios");
		JMenu menuSistema = new JMenu("Sistema");
		
		// Criando submenus de Cadastro
		JMenuItem itemMenuCurso = new JMenuItem("Curso", iconCurso);
		itemMenuCurso.setToolTipText("Gerenciar Curso");
		itemMenuCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ListaCurso tlc = new ListaCurso();
						tlc.setVisible(true);
					}
				});
			}
		});
		
		JMenuItem itemMenuAluno = new JMenuItem("Aluno", iconAluno);
		itemMenuAluno.setToolTipText("Gerenciar Aluno");
		itemMenuAluno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                ListaAluno tla = new ListaAluno();
		                tla.setVisible(true);
		            }
		        });
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
		                ListaProfessor tlp = new ListaProfessor();
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
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                ListaTurma tlt = new ListaTurma();
		                tlt.setVisible(true);
		            }
		        });	
			}
		});
				
		JMenuItem itemMenuDisciplina = new JMenuItem("Disciplina", iconDisciplina);
		itemMenuDisciplina.setToolTipText("Gerenciar Disciplina");
		itemMenuDisciplina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ListaDisciplina tld = new ListaDisciplina();
						tld.setVisible(true);
					}
				});
			}
		});
		
		// Criando submenu de Gestão Acadêmica
		JMenuItem itemMenuMatricular = new JMenuItem("Matricular aluno");
		itemMenuMatricular.setToolTipText("Matricular alunos nas turmas");
		itemMenuMatricular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                ListaTurmaMatricula tltm = new ListaTurmaMatricula();
		                tltm.setVisible(true);
		            }
		        });	
			}
		});
		
		JMenuItem itemMenuDefinirProfessor = new JMenuItem("Definir Professor");
		itemMenuDefinirProfessor.setToolTipText("Definir Professor e Disciplina na turma");
		// TODO: Colocar ação para Definir professor
		
		// Criando submenu de Relatorio
		JMenuItem itemMenuRelAluno = new JMenuItem("Alunos por Turma");
		itemMenuRelAluno.setToolTipText("Relatório de Alunos por Turma");
		// TODO: Colocar ação para alunos por turma
		
		JMenuItem itemMenuRelProfessor = new JMenuItem("Professores por Turma");
		itemMenuRelProfessor.setToolTipText("Relatório de Professores por Turma");
		// TODO: Colocar ação para professor por turma
		
		JMenuItem itemMenuRelDisciplina = new JMenuItem("Disciplina por Turma");
		itemMenuRelDisciplina.setToolTipText("Relatório de Disciplinas por Turma");
		// TODO: Colocar ação para Discplina por turma
		
		
		// Criando submenu de Sistema
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

		// Adicionando os submenus de Cadastro
		menuCadastro.add(itemMenuCurso);
		menuCadastro.add(itemMenuAluno);
		menuCadastro.add(itemMenuProfessor);
		menuCadastro.add(itemMenuTurma);
		menuCadastro.add(itemMenuDisciplina);
		
		// Adicionando os submenus de Gestão Acadêmica
		menuGestao.add(itemMenuMatricular);
		menuGestao.add(itemMenuDefinirProfessor);
		
		// Adicionando os submenus de Relatórios
		menuRelatorio.add(itemMenuRelAluno);
		menuRelatorio.add(itemMenuRelProfessor);
		menuRelatorio.add(itemMenuRelDisciplina);
		
		// Adicionando os submenus de Sistema
		menuSistema.add(itemMenuSair);
		
		// Adicionando os menus na barra
		menuBar.add(menuCadastro);
		menuBar.add(menuGestao);
		menuBar.add(menuRelatorio);
		menuBar.add(menuSistema);
		
		// Adicionando a barra na tela
		setJMenuBar(menuBar);
		
		// Adicionando painel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel);
		
		// Colocando a logo no painel
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(lblLogo, BorderLayout.CENTER);		
		
        // Configurações finais
		setTitle("Academic Net - Sistema de Gestão Acadêmica");
		setIconImage(favicon.getImage());
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
}
