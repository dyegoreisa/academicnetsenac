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

import br.com.senac.dao.DisciplinaDAO;
import br.com.senac.dao.ProfessorDAO;
import br.com.senac.model.Curso;
import br.com.senac.model.Disciplina;
import br.com.senac.model.Professor;

public class EditarDisciplina extends JFrame implements ActionListener {

	private static final long serialVersionUID = 524411140664560533L;
	
	private ImageIcon favicon;
	private Disciplina disciplina;
	private DisciplinaDAO disciplinaDAO;
	private ProfessorDAO professorDAO;
	private JTextField txtNome;
	private JComboBox<Professor> cobProfessores;
	private JButton btnSalvar, btnExcluir, btnFechar;
	
	public EditarDisciplina (Disciplina disciplina) {
        disciplinaDAO = new DisciplinaDAO();
        professorDAO = new ProfessorDAO();
        
        if (disciplina == null) {
        	this.disciplina = new Disciplina();
        } else {
        	this.disciplina = disciplinaDAO.getById(disciplina.getId());
        }
		
		initUI();
	}
	
	private final void initUI() {
		
		favicon = new ImageIcon(getClass().getResource("/images/disciplina16x16.png"));
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Disciplina");
		title.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		topPanel.add(title);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/disciplina48x48.png"));
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.WEST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        // Formulário
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(3, 2));
               
        Font fontLabel = new Font("Verdana", Font.PLAIN, 14);
        Color colorLabel = new Color(50, 50, 25);
                
        // ID:
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(fontLabel);
        lblId.setForeground(colorLabel);
        fieldPanel.add(lblId, BorderLayout.WEST);
        JLabel ltxtId = new JLabel(String.valueOf(disciplina.getId()));
        fieldPanel.add(ltxtId, BorderLayout.EAST);
        
        
        // Nome:
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(colorLabel);
        fieldPanel.add(lblNome, BorderLayout.WEST);
        txtNome = new JTextField(disciplina.getNome());
        fieldPanel.add(txtNome, BorderLayout.EAST);
        
        // Professor
        JLabel lblProfessor = new JLabel("Professor:");
        lblProfessor.setFont(fontLabel);
        lblProfessor.setForeground(colorLabel);
        fieldPanel.add(lblProfessor, BorderLayout.WEST);
        cobProfessores = new JComboBox<Professor>();
        
        int index = 0, count = 0, idProfessor = 0;
        if (disciplina.getProfessor() != null) {
        	idProfessor = disciplina.getProfessor().getId();
        }
        for (Professor professor : professorDAO.listar()) {
        	cobProfessores.addItem(professor);
        	if (professor.getId() == idProfessor) {
        		index = count;
        	}
        	count++;
        }
        cobProfessores.setSelectedIndex(index);
        fieldPanel.add(cobProfessores, BorderLayout.EAST);        

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
        
        setTitle("Disciplina");
        setIconImage(favicon.getImage());
        setSize(new Dimension(450, 220));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSalvar) {
			disciplina.setNome(txtNome.getText());
			String[] aux = cobProfessores.getSelectedItem().toString().split(" - ");
			Professor professor = new Professor(Integer.parseInt(aux[0]), aux[1]);
			disciplina.setProfessor(professor);
			
			if (disciplina.getId() > 0) {
				disciplinaDAO.atualizar(disciplina);
			} else {
				disciplinaDAO.inserir(disciplina);
			}

			ListaDisciplina tlp = new ListaDisciplina();
			tlp.setVisible(true);
			
			dispose();			
		}
		
		if (e.getSource() == btnFechar) {
			ListaDisciplina tlp = new ListaDisciplina();
			tlp.setVisible(true);
			dispose();
		}
		
		if (e.getSource() == btnExcluir) {
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o disciplina de " + disciplina.getNome() + "?",
					"Exluir Disciplina", JOptionPane.YES_NO_OPTION) == 0) {
				disciplinaDAO.apagar(disciplina);
			}
			ListaDisciplina tlp = new ListaDisciplina();
			tlp.setVisible(true);
			
			dispose();
		}
		
	}
	
}
