package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Aluno;
import br.com.senac.model.Turma;

public class RelAlunoTurma extends JFrame implements ActionListener{

	private static final long serialVersionUID = 5849257271905351845L;
	
	private ImageIcon favicon;
	private JButton btnFechar;
	private TurmaDAO turmaDAO;
	private Turma turma;
	private List<Aluno> alunos;
	
	public RelAlunoTurma (Turma turma) {
		turmaDAO = new TurmaDAO();
		this.turma = turmaDAO.getByNome(turma.getNome());
		initUI();
	}
	
	private final void initUI() {

		if (turma != null) {
			alunos = turmaDAO.listarAlunos(turma);
		} else {
			JOptionPane.showMessageDialog(null, "Turma não encontrada");
			dispose();			
		}
		
		favicon = new ImageIcon(getClass().getResource("/images/turma16x16.png"));
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Lista de alunos da turma " + turma.getNome());
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
               

        // Lista de alunos
        JList<Aluno> listAlunos = new JList<Aluno>(new ModelListAluno(alunos));
        JScrollPane paneListaAlunos = new JScrollPane();
        paneListaAlunos.getViewport().add(listAlunos);
        fieldPanel.add(paneListaAlunos, BorderLayout.WEST);
      
        
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnFechar = new JButton("Fechar");
        btnFechar.setMnemonic(KeyEvent.VK_F);
        btnFechar.addActionListener(this);
        
        bottom.add(btnFechar);
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));

        setTitle("Relatório de Alunos por Turma");
        setIconImage(favicon.getImage());
        setSize(new Dimension(600, 400));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFechar) {
			dispose();
		}
	}

	public class ModelListAluno extends AbstractListModel<Aluno> {

		private static final long serialVersionUID = -6382215151854365504L;

		private List<Aluno> alunos;
		
		public ModelListAluno(List<Aluno> alunos) {
			this.alunos = alunos; 
		}
		
		@Override
		public int getSize() {
			return alunos.size();
		}

		@Override
		public Aluno getElementAt(int index) {
			return alunos.get(index);
		}
		
		public List<Aluno> getArray() {
			return alunos;
		}
				
	}	
	
}
