package br.com.senac.view.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.senac.dao.ProfessorDAO;
import br.com.senac.model.Professor;

public class TelaEditarProfessor extends JFrame {

	private static final long serialVersionUID = 8174800542868923064L;

	public TelaEditarProfessor () {
		initUI();
	}
	
	private final void initUI() {
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Professor");
		title.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		topPanel.add(title);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/professor48x48.png"));
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.WEST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(10, 2));
               
        Font fontLabel = new Font("Verdana", Font.PLAIN, 14);
        Color colorLabel = new Color(50, 50, 25);
        
        ProfessorDAO profDAO = new ProfessorDAO();
        Professor professor = profDAO.getById(0);
        professor = (professor == null) ? new Professor() : professor;
        
        // ID:
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(fontLabel);
        lblId.setForeground(colorLabel);
        fieldPanel.add(lblId, BorderLayout.WEST);
        JTextField txtId = new JTextField(String.valueOf(professor.getId()));
        fieldPanel.add(txtId, BorderLayout.EAST);        	
        
        // Nome:
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(colorLabel);
        fieldPanel.add(lblNome, BorderLayout.WEST);
        JTextField txtNome = new JTextField(professor.getNome());
        fieldPanel.add(txtNome, BorderLayout.EAST);  
        
        // Sobrenome:
        JLabel lblSobrenome = new JLabel("Sobrenome:");
        lblSobrenome.setFont(fontLabel);
        lblSobrenome.setForeground(colorLabel);
        fieldPanel.add(lblSobrenome, BorderLayout.WEST);
        JTextField txtSobrenome = new JTextField(professor.getSobrenome());
        fieldPanel.add(txtSobrenome, BorderLayout.EAST); 
        
        // Sexo:
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(fontLabel);
        lblSexo.setForeground(colorLabel);
        fieldPanel.add(lblSexo, BorderLayout.WEST);
        JTextField txtSexo = new JTextField(professor.getSexo());
        fieldPanel.add(txtSexo, BorderLayout.EAST); 
        
        // Telefone:
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(fontLabel);
        lblTelefone.setForeground(colorLabel);
        fieldPanel.add(lblTelefone, BorderLayout.WEST);
        JTextField txtTelefone = new JTextField(professor.getTelefone());
        fieldPanel.add(txtTelefone, BorderLayout.EAST); 
        
        // Data de Nacimento:
        JLabel lblNascimento = new JLabel("Nascimento:");
        lblNascimento.setFont(fontLabel);
        lblNascimento.setForeground(colorLabel);
        fieldPanel.add(lblNascimento, BorderLayout.WEST);
        JTextField txtNascimento = new JTextField(professor.getDataNascimento(new SimpleDateFormat("dd/mm/yyyy")));
        fieldPanel.add(txtNascimento, BorderLayout.EAST); 
        
        // E-mail:
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(fontLabel);
        lblEmail.setForeground(colorLabel);
        fieldPanel.add(lblEmail, BorderLayout.WEST);
        JTextField txtEmail = new JTextField(professor.getEmail());
        fieldPanel.add(txtEmail, BorderLayout.EAST);
        
        // Especialidade:
        JLabel lblEspecialidade = new JLabel("Especialidade:");
        lblEspecialidade.setFont(fontLabel);
        lblEspecialidade.setForeground(colorLabel);
        fieldPanel.add(lblEspecialidade, BorderLayout.WEST);
        JTextField txtEspecialidade = new JTextField(professor.getEspecialidade());
        fieldPanel.add(txtEspecialidade, BorderLayout.EAST); 
        
        // Salario:
        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setFont(fontLabel);
        lblSalario.setForeground(colorLabel);
        fieldPanel.add(lblSalario, BorderLayout.WEST);
        JTextField txtSalario = new JTextField(String.valueOf(professor.getSalario()));
        fieldPanel.add(txtSalario, BorderLayout.EAST);
        
        // Vinculo:
        JLabel lblVinculo = new JLabel("Vinculo:");
        lblVinculo.setFont(fontLabel);
        lblVinculo.setForeground(colorLabel);
        fieldPanel.add(lblVinculo, BorderLayout.WEST);
        JTextField txtVinculo = new JTextField(professor.getVinculo());
        fieldPanel.add(txtVinculo, BorderLayout.EAST); 

        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setMnemonic(KeyEvent.VK_S);
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setMnemonic(KeyEvent.VK_F);

        bottom.add(btnSalvar);
        bottom.add(btnFechar);
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));

        setTitle("Professor");
        setSize(new Dimension(450, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
}
