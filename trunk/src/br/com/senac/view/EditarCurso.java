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

import br.com.senac.dao.CursoDAO;
import br.com.senac.model.Curso;

public class EditarCurso extends JFrame implements ActionListener {

	private static final long serialVersionUID = 524411140664560533L;
	
	private Curso curso;
	private CursoDAO cursoDAO;
	private JTextField txtNome;
	private JButton btnSalvar, btnExcluir, btnFechar;
	
	public EditarCurso (Curso curso) {
        cursoDAO = new CursoDAO();
        
        if (curso == null) {
        	this.curso = new Curso();
        } else {
        	this.curso = cursoDAO.getById(curso.getId());
        }
		
		initUI();
	}
	
	private final void initUI() {
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Curso");
		title.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		topPanel.add(title);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/curso48x48.png"));
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.WEST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        // Formulário
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(2, 2));
               
        Font fontLabel = new Font("Verdana", Font.PLAIN, 14);
        Color colorLabel = new Color(50, 50, 25);
                
        // ID:
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(fontLabel);
        lblId.setForeground(colorLabel);
        fieldPanel.add(lblId, BorderLayout.WEST);
        JLabel ltxtId = new JLabel(String.valueOf(curso.getId()));
        fieldPanel.add(ltxtId, BorderLayout.EAST);
        
        
        // Nome:
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(colorLabel);
        fieldPanel.add(lblNome, BorderLayout.WEST);
        txtNome = new JTextField(curso.getNome());
        fieldPanel.add(txtNome, BorderLayout.EAST);

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
        
        setTitle("Curso");
        setSize(new Dimension(450, 180));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSalvar) {
			curso.setNome(txtNome.getText());
			
			if (curso.getId() > 0) {
				cursoDAO.atualizar(curso);
			} else {
				cursoDAO.inserir(curso);
			}

			ListaCurso tlp = new ListaCurso();
			tlp.setVisible(true);
			
			dispose();			
		}
		
		if (e.getSource() == btnFechar) {
			ListaCurso tlp = new ListaCurso();
			tlp.setVisible(true);
			dispose();
		}
		
		if (e.getSource() == btnExcluir) {
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o curso de " + curso.getNome() + "?",
					"Exluir Curso", JOptionPane.YES_NO_OPTION) == 0) {
				cursoDAO.apagar(curso);
			}
			ListaCurso tlp = new ListaCurso();
			tlp.setVisible(true);
			
			dispose();
		}
		
	}
	
}
