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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import org.hibernate.Session;

import br.com.senac.dao.Conexao;
import br.com.senac.dao.ProfessorDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Professor;
import br.com.senac.model.Turma;

public class AdicionarProfessorTurma extends JFrame implements ActionListener, MouseListener  {

	private static final long serialVersionUID = 714672937069102260L;
	
	private ImageIcon favicon;
	private Turma turma;
	private ProfessorDAO professorDAO;
	private TurmaDAO turmaDAO;
	private int id;
	private JButton btnSalvar, btnFechar;
	private JList<Professor> listProfessores, listProfessoresVinculados;
	
	public AdicionarProfessorTurma (Turma turma) {
        if (turma == null) {
        	JOptionPane.showMessageDialog(this, "Deve ser selecionada uma turma.");
        	dispose();
        } else {
        	this.turma = turma;
        }

        professorDAO = new ProfessorDAO();
        turmaDAO = new TurmaDAO();
        
		initUI();
	}
	
	private final void initUI() {
		
		favicon = new ImageIcon(getClass().getResource("/images/turma16x16.png"));
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Adicionar professores a Turma");
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

        Font fontLabel = new Font("Verdana", Font.PLAIN, 14);
        Color colorLabel = new Color(50, 50, 25);
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(4,2));

        // ID:
        id = turma.getId();
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(fontLabel);
        lblId.setForeground(colorLabel);
        labelPanel.add(lblId, BorderLayout.WEST);
        JLabel ltxtId = new JLabel(String.valueOf(turma.getId()));
        labelPanel.add(ltxtId, BorderLayout.EAST);
        
        // Nome:
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(fontLabel);
        lblNome.setForeground(colorLabel);
        labelPanel.add(lblNome, BorderLayout.WEST);
        JLabel lxtNome = new JLabel(turma.getNome());
        labelPanel.add(lxtNome, BorderLayout.EAST);

        // Labels Lista de alunos
        JLabel lblListaAlunos = new JLabel("Lista de Professores:");
        lblListaAlunos.setFont(fontLabel);
        lblListaAlunos.setForeground(colorLabel);
        labelPanel.add(lblListaAlunos, BorderLayout.WEST);
        
        // Labels Lista de Alunos Matriculados
        JLabel lblListaAlunoMatriculados = new JLabel("Lista de Professores Vinculados:");
        lblListaAlunoMatriculados.setFont(fontLabel);
        lblListaAlunoMatriculados.setForeground(colorLabel);
        labelPanel.add(lblListaAlunoMatriculados, BorderLayout.EAST);

        labelPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(labelPanel);      

        
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 2));
        
        // Lista de alunos
        listProfessores = new JList<Professor>(new ModelListProfessor(professorDAO.listar()));
        listProfessores.addMouseListener(this);
        JScrollPane paneListaProfessor = new JScrollPane();
        paneListaProfessor.getViewport().add(listProfessores);
        fieldPanel.add(paneListaProfessor, BorderLayout.WEST);
        
        // Lista de alunos Matriculados
        listProfessoresVinculados = new JList<Professor>(new ModelListProfessor(turmaDAO.listarProfessores(turma)));
        listProfessoresVinculados.addMouseListener(this);
        JScrollPane paneListaProfessoresVinculados = new JScrollPane();
        paneListaProfessoresVinculados.getViewport().add(listProfessoresVinculados);
        fieldPanel.add(paneListaProfessoresVinculados, BorderLayout.EAST);
        
        		
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnSalvar = new JButton("Salvar");
        btnSalvar.setMnemonic(KeyEvent.VK_S);
        btnSalvar.addActionListener(this);
        bottom.add(btnSalvar);
                
        btnFechar = new JButton("Fechar");
        btnFechar.setMnemonic(KeyEvent.VK_F);
        btnFechar.addActionListener(this);
        bottom.add(btnFechar);
        
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));
        
        setTitle("Adicionar professor à Turma");
        setIconImage(favicon.getImage());
        setSize(new Dimension(550, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			ModelListProfessor modelListProf = (ModelListProfessor) listProfessoresVinculados.getModel();
			List<Professor> arrayProfessores = modelListProf.getArray();
			
			turmaDAO.atualizar(turma);
			
			ListaTurmaProfessor tltp = new ListaTurmaProfessor();
			tltp.setVisible(true);
			
			dispose();
		}
		
		if (e.getSource() == btnFechar) {
			ListaTurmaMatricula tltm = new ListaTurmaMatricula();
			tltm.setVisible(true);
			
			dispose();			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			if (e.getSource() == listProfessores) {
				ModelListProfessor modelListProf = (ModelListProfessor) listProfessoresVinculados.getModel();
				List<Professor> arrayProfessor = modelListProf.getArray();
				
				Professor professor = listProfessores.getSelectedValue();
				
				turma = turmaDAO.addProfessor(turma, professor);
				
				arrayProfessor.add(professor);
				
				ModelListProfessor modelProfessor = new ModelListProfessor(arrayProfessor);
				listProfessoresVinculados.setModel(modelProfessor);
			}
			
			if (e.getSource() == listProfessoresVinculados) {
				int index = listProfessoresVinculados.getSelectedIndex();
				listProfessoresVinculados.remove(index);
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
	public class ModelListProfessor extends AbstractListModel<Professor> {

		private static final long serialVersionUID = -6382215151854365504L;

		private List<Professor> professores;
		
		public ModelListProfessor(List<Professor> professores) {
			this.professores = professores; 
		}
		
		@Override
		public int getSize() {
			return professores.size();
		}

		@Override
		public Professor getElementAt(int index) {
			return professores.get(index);
		}
		
		public List<Professor> getArray() {
			return professores;
		}
				
	}

}
