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

import br.com.senac.dao.ProfessorDAO;
import br.com.senac.model.Professor;

public class TelaEditarProfessor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8174800542868923064L;

	private Professor professor;
	private ProfessorDAO profDAO;
	private JTextField txtNome, txtSobrenome, txtTelefone, txtNascimento;
	private JTextField txtEmail, txtEspecialidade, txtSalario;
	private JComboBox<String> cobSexo, cobVinculo;;
	private JButton btnVisualizar, btnSalvar, btnExcluir, btnFechar;
	
	public TelaEditarProfessor (Professor professor) {
        profDAO = new ProfessorDAO();
        
        if (professor == null) {
        	this.professor = new Professor();
        } else {
        	this.professor = profDAO.getById(professor.getId());
        }
		
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
                
        // ID:
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(fontLabel);
        lblId.setForeground(colorLabel);
        fieldPanel.add(lblId, BorderLayout.WEST);
        JLabel ltxtId = new JLabel(String.valueOf(professor.getId()));
        fieldPanel.add(ltxtId, BorderLayout.EAST);
        
        
        // Nome:
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(colorLabel);
        fieldPanel.add(lblNome, BorderLayout.WEST);
        txtNome = new JTextField(professor.getNome());
        fieldPanel.add(txtNome, BorderLayout.EAST);
        
        
        // Sobrenome:
        JLabel lblSobrenome = new JLabel("Sobrenome:");
        lblSobrenome.setFont(fontLabel);
        lblSobrenome.setForeground(colorLabel);
        fieldPanel.add(lblSobrenome, BorderLayout.WEST);
        txtSobrenome = new JTextField(professor.getSobrenome());
        fieldPanel.add(txtSobrenome, BorderLayout.EAST); 
        
        
        // Sexo:
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(fontLabel);
        lblSexo.setForeground(colorLabel);
        fieldPanel.add(lblSexo, BorderLayout.WEST);
        cobSexo = new JComboBox<String>();
        cobSexo.addItem("Masculino");
        cobSexo.addItem("Feminino");
        cobSexo.setSelectedIndex(professor.getSexoInt());
        fieldPanel.add(cobSexo, BorderLayout.EAST);
        
        
        // Telefone:
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(fontLabel);
        lblTelefone.setForeground(colorLabel);
        fieldPanel.add(lblTelefone, BorderLayout.WEST);
        txtTelefone = new JTextField(professor.getTelefone());
        fieldPanel.add(txtTelefone, BorderLayout.EAST);
        
        
        // Data de Nacimento:
        JLabel lblNascimento = new JLabel("Nascimento:");
        lblNascimento.setFont(fontLabel);
        lblNascimento.setForeground(colorLabel);
        fieldPanel.add(lblNascimento, BorderLayout.WEST);
        txtNascimento = new JTextField(professor.getDataNascimento(new SimpleDateFormat("dd/mm/yyyy")));
        fieldPanel.add(txtNascimento, BorderLayout.EAST); 
        
        
        // E-mail:
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(fontLabel);
        lblEmail.setForeground(colorLabel);
        fieldPanel.add(lblEmail, BorderLayout.WEST);
        txtEmail = new JTextField(professor.getEmail());
        fieldPanel.add(txtEmail, BorderLayout.EAST);
        
        
        // Especialidade:
        JLabel lblEspecialidade = new JLabel("Especialidade:");
        lblEspecialidade.setFont(fontLabel);
        lblEspecialidade.setForeground(colorLabel);
        fieldPanel.add(lblEspecialidade, BorderLayout.WEST);
        txtEspecialidade = new JTextField(professor.getEspecialidade());
        fieldPanel.add(txtEspecialidade, BorderLayout.EAST); 
        
        
        // Salario:
        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setFont(fontLabel);
        lblSalario.setForeground(colorLabel);
        fieldPanel.add(lblSalario, BorderLayout.WEST);
        txtSalario = new JTextField(String.valueOf(professor.getSalario()));
        fieldPanel.add(txtSalario, BorderLayout.EAST);
        
        
        // Vinculo:
        JLabel lblVinculo = new JLabel("Vinculo:");
        lblVinculo.setFont(fontLabel);
        lblVinculo.setForeground(colorLabel);
        fieldPanel.add(lblVinculo, BorderLayout.WEST);
        cobVinculo = new JComboBox<String>();
        cobVinculo.addItem("CLT");
        cobVinculo.addItem("Bolsa");
        cobVinculo.addItem("PJ");
        cobVinculo.addItem("Servidor");
        fieldPanel.add(cobVinculo, BorderLayout.EAST); 
        

        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnVisualizar = new JButton("Ver Alunos");
        btnVisualizar.setMnemonic(KeyEvent.VK_V);
        btnVisualizar.addActionListener(this);
        bottom.add(btnVisualizar);
        
        
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
        
        setTitle("Professor");
        setSize(new Dimension(450, 450));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVisualizar) {
			System.out.println(profDAO.listarAlunosByProfessor(professor.getId()));
			JOptionPane.showMessageDialog(this, profDAO.listarAlunosByProfessor(professor.getId()));
		}
		
		if (e.getSource() == btnSalvar) {
			//professor.setId(Integer.parseInt(txtId.toString()));
			professor.setNome(txtNome.getText());
			professor.setSobrenome(txtSobrenome.getText());
			professor.setSexoInt(cobSexo.getSelectedIndex());
			professor.setTelefone(txtTelefone.getText());
			professor.setDataNascimento(txtNascimento.getText());
			professor.setEmail(txtEmail.getText());
			professor.setEspecialidade(txtEspecialidade.getText());
			professor.setVinculo(cobVinculo.getSelectedItem().toString());
			professor.setSalario(Double.parseDouble(txtSalario.getText()));	
			
			if (professor.getId() > 0) {
				profDAO.atualizar(professor);
			} else {
				profDAO.inserir(professor);
			}
			TelaListaProfessor tlp = new TelaListaProfessor();
			tlp.setVisible(true);
			
			dispose();
		}
		
		if (e.getSource() == btnFechar) {
			dispose();
		}
		
		if (e.getSource() == btnExcluir) {
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente escluir o professor?",
					"Exluir Professor", JOptionPane.YES_NO_OPTION) == 0) {
				profDAO.apagar(professor);
			}
			TelaListaProfessor tlp = new TelaListaProfessor();
			tlp.setVisible(true);
			
			dispose();
		}
		
	}
	
}
