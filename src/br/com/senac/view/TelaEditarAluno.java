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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.senac.dao.AlunoDAO;
import br.com.senac.model.Aluno;

public class TelaEditarAluno extends JFrame implements ActionListener {

	private static final long serialVersionUID = 524411140664560533L;
	
	private Aluno aluno;
	private AlunoDAO alunoDAO;
	private JTextField txtNome, txtSobrenome, txtTelefone, txtNascimento;
	private JTextField txtEmail, txtMatricula;
	private JComboBox<String> cobSexo;
	private JCheckBox chbBolsa;
	private JButton btnVisualizar, btnSalvar, btnExcluir, btnFechar;
	
	public TelaEditarAluno (Aluno aluno) {
        alunoDAO = new AlunoDAO();
        
        if (aluno == null) {
        	this.aluno = new Aluno();
        } else {
        	this.aluno = alunoDAO.getById(aluno.getId());
        }
		
		initUI();
	}
	
	private final void initUI() {
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Aluno");
		title.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		topPanel.add(title);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/aluno48x48.png"));
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
        JLabel ltxtId = new JLabel(String.valueOf(aluno.getId()));
        fieldPanel.add(ltxtId, BorderLayout.EAST);
        
        
        // Nome:
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(colorLabel);
        fieldPanel.add(lblNome, BorderLayout.WEST);
        txtNome = new JTextField(aluno.getNome());
        fieldPanel.add(txtNome, BorderLayout.EAST);
        
        
        // Sobrenome:
        JLabel lblSobrenome = new JLabel("Sobrenome:");
        lblSobrenome.setFont(fontLabel);
        lblSobrenome.setForeground(colorLabel);
        fieldPanel.add(lblSobrenome, BorderLayout.WEST);
        txtSobrenome = new JTextField(aluno.getSobrenome());
        fieldPanel.add(txtSobrenome, BorderLayout.EAST); 
        
        
        // Sexo:
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(fontLabel);
        lblSexo.setForeground(colorLabel);
        fieldPanel.add(lblSexo, BorderLayout.WEST);
        //txtSexo = new JTextField(aluno.getSexo());
        cobSexo = new JComboBox<String>();
        cobSexo.addItem("Masculino");
        cobSexo.addItem("Feminino");
        cobSexo.setSelectedIndex(aluno.getSexoInt());
        fieldPanel.add(cobSexo, BorderLayout.EAST);
        
        
        // Telefone:
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(fontLabel);
        lblTelefone.setForeground(colorLabel);
        fieldPanel.add(lblTelefone, BorderLayout.WEST);
        txtTelefone = new JTextField(aluno.getTelefone());
        fieldPanel.add(txtTelefone, BorderLayout.EAST);
        
        
        // Data de Nacimento:
        JLabel lblNascimento = new JLabel("Nascimento:");
        lblNascimento.setFont(fontLabel);
        lblNascimento.setForeground(colorLabel);
        fieldPanel.add(lblNascimento, BorderLayout.WEST);
        txtNascimento = new JTextField(aluno.getDataNascimento(new SimpleDateFormat("dd/MM/yyyy")));
        fieldPanel.add(txtNascimento, BorderLayout.EAST); 
        
        
        // E-mail:
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(fontLabel);
        lblEmail.setForeground(colorLabel);
        fieldPanel.add(lblEmail, BorderLayout.WEST);
        txtEmail = new JTextField(aluno.getEmail());
        fieldPanel.add(txtEmail, BorderLayout.EAST);
        
        
        // Matricula:
        JLabel lblMatricula = new JLabel("Matricula:");
        lblMatricula.setFont(fontLabel);
        lblMatricula.setForeground(colorLabel);
        fieldPanel.add(lblMatricula, BorderLayout.WEST);
        txtMatricula = new JTextField(aluno.getMatricula().toString());
        fieldPanel.add(txtMatricula, BorderLayout.EAST); 
        
        
        // Bolsa:
        JLabel lblBolsa = new JLabel("Bolsa:");
        lblBolsa.setFont(fontLabel);
        lblBolsa.setForeground(colorLabel);
        fieldPanel.add(lblBolsa, BorderLayout.WEST);
        chbBolsa = new JCheckBox();
        chbBolsa.setSelected(aluno.getBolsa());
        fieldPanel.add(chbBolsa, BorderLayout.EAST);
        

        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnVisualizar = new JButton("Ver Professor");
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
        
        setTitle("Aluno");
        setSize(new Dimension(450, 450));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVisualizar) {
			JOptionPane.showMessageDialog(this, alunoDAO.listarProfessoresByAluno(aluno.getId()));
		}
		
		if (e.getSource() == btnSalvar) {
			aluno.setNome(txtNome.getText());
			aluno.setSobrenome(txtSobrenome.getText());
			aluno.setSexoInt(cobSexo.getSelectedIndex());
			aluno.setTelefone(txtTelefone.getText());
			aluno.setDataNascimento(txtNascimento.getText());
			aluno.setEmail(txtEmail.getText());
			try { 
				aluno.setMatricula(Integer.parseInt(txtMatricula.getText()));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			aluno.setBolsa(chbBolsa.isSelected());
			
			if (aluno.getId() > 0) {
				alunoDAO.atualizar(aluno);
			} else {
				alunoDAO.inserir(aluno);
			}
			TelaListaAluno tlp = new TelaListaAluno();
			tlp.setVisible(true);
			
			dispose();			
		}
		
		if (e.getSource() == btnFechar) {
			TelaListaAluno tlp = new TelaListaAluno();
			tlp.setVisible(true);
			dispose();
		}
		
		if (e.getSource() == btnExcluir) {
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente escluir o aluno?",
					"Exluir Aluno", JOptionPane.YES_NO_OPTION) == 0) {
				alunoDAO.apagar(aluno);
			}
			TelaListaAluno tlp = new TelaListaAluno();
			tlp.setVisible(true);
			
			dispose();
		}
		
	}
	
}
