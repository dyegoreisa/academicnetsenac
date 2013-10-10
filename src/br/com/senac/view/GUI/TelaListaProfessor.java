package br.com.senac.view.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

import br.com.senac.model.Professor;

public class TelaListaProfessor extends JFrame {

	private static final long serialVersionUID = 5849257271905351845L;
	
	public TelaListaProfessor () {
		initUI();
	}
	
	private final void initUI() {
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Lista de professores");
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
        
        // TODO: trecho deve ser alterado para buscar do banco
        ArrayList<Professor> professores = new ArrayList<Professor>();
        professores.add(new Professor("Rogerio", "Saraiva", "rogerio@saraiva", "DBA", 3200.00, "CLT"));
        professores.add(new Professor("Vanessa", "Medeiros", "vanessa@saraiva", "Analista", 3800.00, "Bolsa"));
        professores.add(new Professor("Beatriz", "Azevedo", "beatriz@saraiva", "Engenheira", 4500.00, "PJ"));
        professores.add(new Professor("Arthur", "Reis", "arthur@saraiva", "Redes", 2600.00, "CLT"));
        
        JList listProfessores = new JList(professores.toArray());

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(listProfessores);
        pane.setPreferredSize(new Dimension(500, 250));
        fieldPanel.add(pane);
        
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnNovo = new JButton("Novo");
        btnNovo.setMnemonic(KeyEvent.VK_N);
        btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                TelaEditarProfessor tep = new TelaEditarProfessor();
		                tep.setVisible(true);
		            }
		        });
			}
		});

        bottom.add(btnNovo);
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));

        setTitle("Professor");
        setSize(new Dimension(600, 400));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
}
