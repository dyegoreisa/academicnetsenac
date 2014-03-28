package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.senac.dao.CursoDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Curso;
import br.com.senac.model.Turma;

public class EditarTurma extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8174800542868923064L;
	
	private Turma turma;
	private TurmaDAO turmaDAO;
	private CursoDAO cursoDAO;
	private JTextField txtNome, txtDataInicio, txtPrevisao, txtDataFim;
	private JComboBox<Curso> cobCursos;
	private JButton btnSalvar, btnExcluir, btnFechar;
	private SimpleDateFormat formatoData;
	
	public EditarTurma (Turma turma) {
        turmaDAO = new TurmaDAO();
        cursoDAO = new CursoDAO();
        formatoData = new SimpleDateFormat("dd/mm/yyyy");
        
        if (turma == null) {
        	this.turma = new Turma();
        } else {
        	this.turma = turmaDAO.getById(turma.getId());
        }
		
		initUI();
	}
	
	private final void initUI() {
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Turma");
		title.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		topPanel.add(title);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/turma48x48.png"));
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.WEST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        // Formulário
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(6, 2));
               
        Font fontLabel = new Font("Verdana", Font.PLAIN, 14);
        Color colorLabel = new Color(50, 50, 25);
                
        // ID:
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(fontLabel);
        lblId.setForeground(colorLabel);
        fieldPanel.add(lblId, BorderLayout.WEST);
        JLabel ltxtId = new JLabel(String.valueOf(turma.getId()));
        fieldPanel.add(ltxtId, BorderLayout.EAST);
        
        
        // Nome:
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(colorLabel);
        fieldPanel.add(lblNome, BorderLayout.WEST);
        txtNome = new JTextField(turma.getNome());
        fieldPanel.add(txtNome, BorderLayout.EAST);

        // data inicio
        JLabel lblDataInicio = new JLabel("Data de inicio da turma:");
        lblDataInicio.setFont(fontLabel);
        lblDataInicio.setForeground(colorLabel);
        fieldPanel.add(lblDataInicio, BorderLayout.WEST);
        try {
        	txtDataInicio = new JTextField(formatoData.format(turma.getDataInicio()));
        } catch (NullPointerException e) {
        	txtDataInicio = new JTextField();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        fieldPanel.add(txtDataInicio, BorderLayout.EAST);

        // previsao de termino
        JLabel lblPrevisao = new JLabel("Data de previsão de término:");
        lblPrevisao.setFont(fontLabel);
        lblPrevisao.setForeground(colorLabel);
        fieldPanel.add(lblPrevisao, BorderLayout.WEST);
        try {
        	txtPrevisao = new JTextField(formatoData.format(turma.getPrevisaoTermino()));
        } catch (NullPointerException e) {
        	txtPrevisao = new JTextField();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        fieldPanel.add(txtPrevisao, BorderLayout.EAST);

        // data fim
        JLabel lblDataFim = new JLabel("Data do fim da turma:");
        lblDataFim.setFont(fontLabel);
        lblDataFim.setForeground(colorLabel);
        fieldPanel.add(lblDataFim, BorderLayout.WEST);
        try {
        	txtDataFim = new JTextField(formatoData.format(turma.getDataFim()));
        } catch (NullPointerException e) {
        	txtDataFim = new JTextField();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        fieldPanel.add(txtDataFim, BorderLayout.EAST);

        // curso
        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setFont(fontLabel);
        lblCurso.setForeground(colorLabel);
        fieldPanel.add(lblCurso, BorderLayout.WEST);
        cobCursos = new JComboBox<Curso>();
        int index = 0, count = 0;
        for (Curso curso : cursoDAO.listar()) {
        	cobCursos.addItem(curso);
        	if (curso.getId() == turma.getCurso().getId()) {
        		index = count;
        	}
        	count++;
        }
        cobCursos.setSelectedIndex(index);
        fieldPanel.add(cobCursos, BorderLayout.EAST);

        
        
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        // Botões
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnSalvar = new JButton("Salvar");
        btnSalvar.setMnemonic(KeyEvent.VK_S);
        btnSalvar.addActionListener(this);
        bottom.add(btnSalvar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setMnemonic(KeyEvent.VK_E);
        btnExcluir.addActionListener(this);
        bottom.add(btnExcluir);
                
        btnFechar = new JButton("Fechar");
        btnFechar.setMnemonic(KeyEvent.VK_F);
        btnFechar.addActionListener(this);
        bottom.add(btnFechar);
        
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));
        
        setTitle("Turma");
        setSize(new Dimension(450, 300));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSalvar) {
			turma.setNome(txtNome.getText());
			try {
				turma.setDataInicio(formatoData.parse(txtDataInicio.getText().toString()));
				turma.setPrevisaoTermino(formatoData.parse(txtPrevisao.getText().toString()));
				turma.setDataFim(formatoData.parse(txtDataFim.getText().toString()));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			
			String[] aux = cobCursos.getSelectedItem().toString().split(" - ");
			Curso curso = new Curso(Integer.parseInt(aux[0]), aux[1]);
			turma.setCurso(curso);
			
			if (turma.getId() > 0) {
				turmaDAO.atualizar(turma);
			} else {
				turmaDAO.inserir(turma);
			}

			ListaTurma tlp = new ListaTurma();
			tlp.setVisible(true);
			
			dispose();			
		}
		
		if (e.getSource() == btnFechar) {
			ListaTurma tlp = new ListaTurma();
			tlp.setVisible(true);
			dispose();
		}
		
		if (e.getSource() == btnExcluir) {
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o turma de " + turma.getNome() + "?",
					"Exluir Turma", JOptionPane.YES_NO_OPTION) == 0) {
				turmaDAO.apagar(turma);
			}
			ListaTurma tlp = new ListaTurma();
			tlp.setVisible(true);
			
			dispose();
		}
		
	}
	
}
