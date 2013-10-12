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
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Turma;

public class TelaEditarTurma extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8174800542868923064L;

	private Turma turma;
	private TurmaDAO turmaDAO;
	private JTextField txtNome;
	private JButton btnSalvar, btnExcluir, btnFechar;
	
	public TelaEditarTurma (Turma turma) {
        turmaDAO = new TurmaDAO();
        
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

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(10, 2));
               
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
                

        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

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
        setSize(new Dimension(450, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			//turma.setId(Integer.parseInt(txtId.toString()));
			turma.setNome(txtNome.getText());
			
			if (turma.getId() > 0) {
				turmaDAO.atualizar(turma);
			} else {
				turmaDAO.inserir(turma);
			}
		}
		
//		if (e.getSource() == btnFechar) {
//			dispose();
//		}
		
		if (e.getSource() == btnExcluir) {
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente escluir o turma?",
					"Exluir Turma", JOptionPane.YES_NO_OPTION) == 0) {
				turmaDAO.apagar(turma);
			}
		}
		
		TelaListaTurma tlp = new TelaListaTurma();
		tlp.setVisible(true);
		
		dispose();
	}
	
}
