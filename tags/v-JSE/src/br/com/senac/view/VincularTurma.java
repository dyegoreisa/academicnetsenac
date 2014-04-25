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
import java.util.ArrayList;
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

import br.com.senac.dao.AlunoDAO;
import br.com.senac.dao.ProfessorDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Aluno;
import br.com.senac.model.Professor;
import br.com.senac.model.Turma;

public class VincularTurma extends JFrame implements ActionListener, MouseListener  {

	private static final long serialVersionUID = 714672937069102260L;
	
	private ImageIcon favicon;
	private Turma turma;
	private TurmaDAO turmaDAO;
	private AlunoDAO alunoDAO;
	private ProfessorDAO professorDAO;
	private int id;
	private JButton btnSalvar, btnFechar;
	private JList<Aluno> listAlunos, listAlunosVinculados;
	private JList<Professor> listProfessores, listProfessoresVinculados;
	
	public VincularTurma (Turma turma) {
        turmaDAO = new TurmaDAO();
        alunoDAO = new AlunoDAO();
        professorDAO = new ProfessorDAO();
        
        if (turma == null) {
        	JOptionPane.showMessageDialog(this, "Deve ser selecionada uma turma.");
        	dispose();
        } else {
        	this.turma = turmaDAO.getById(turma.getId());
        }
		
		initUI();
	}
	
	private final void initUI() {
		
		favicon = new ImageIcon(getClass().getResource("/images/turma16x16.png"));
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Vincular à Turma");
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
        fieldPanel.setLayout(new GridLayout(4, 2));
               
        Font fontLabel = new Font("Verdana", Font.PLAIN, 14);
        Color colorLabel = new Color(50, 50, 25);
                
        // ID:
        id = turma.getId();
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
        JLabel lxtNome = new JLabel(turma.getNome());
        fieldPanel.add(lxtNome, BorderLayout.EAST);
        
        // Lista de alunos
        listAlunos = new JList<Aluno>(new ModelListAluno(alunoDAO.listar()));
        listAlunos.addMouseListener(this);
        JScrollPane paneListaAlunos = new JScrollPane();
        paneListaAlunos.getViewport().add(listAlunos);
        fieldPanel.add(paneListaAlunos);
        
        // Lista de alunos vinculados
        /*listAlunosVinculados = new JList<Aluno>(new ModelListAluno(turmaDAO.listarAlunos(turma.getId())));
        JScrollPane paneListaAlunosVinculados = new JScrollPane();
        paneListaAlunosVinculados .getViewport().add(listAlunosVinculados);
        fieldPanel.add(paneListaAlunosVinculados);
        */
        
        // Lista de professores
        listProfessores = new JList<Professor>(new ModelListProfessor(professorDAO.listar()));
        listProfessores.addMouseListener(this);
        JScrollPane paneListaProfessores = new JScrollPane();
        paneListaProfessores.getViewport().add(listProfessores);
        fieldPanel.add(paneListaProfessores);
        
        // Lista de professores vinculados
        /*listProfessoresVinculados = new JList<Professor>(new ModelListProfessor(turmaDAO.listarProfessores(turma.getId())));
        JScrollPane paneListaProfessoresVinculados = new JScrollPane();
        paneListaProfessoresVinculados.getViewport().add(listProfessoresVinculados);
        fieldPanel.add(paneListaProfessoresVinculados);  
		*/
		
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
        
        setTitle("Vincular à Turma");
        setIconImage(favicon.getImage());
        setSize(new Dimension(450, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			ModelListAluno modelListAlu = (ModelListAluno) listAlunosVinculados.getModel();
			List<Aluno> arrayAlunos = modelListAlu.getArray();
			
			ModelListProfessor modelListProf = (ModelListProfessor) listProfessoresVinculados.getModel();
			List<Professor> arrayProfessores = modelListProf.getArray();
			
			//turmaDAO.vincular(id, arrayAlunos, arrayProfessores);
		}
		
		ListaTurma tlt = new ListaTurma();
		tlt.setVisible(true);
		
		dispose();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			if (e.getSource() == listAlunos) {
				ModelListAluno modelListAlu = (ModelListAluno) listAlunosVinculados.getModel();
				List<Aluno> arrayAlunos = modelListAlu.getArray();
				arrayAlunos.add(listAlunos.getSelectedValue());
				ModelListAluno modelAlunos = new ModelListAluno(arrayAlunos);
				listAlunosVinculados.setModel(modelAlunos);
			}
			
			if (e.getSource() == listProfessores) {
				ModelListProfessor modelListProf = (ModelListProfessor) listProfessoresVinculados.getModel();
				List<Professor> arrayProfessores = modelListProf.getArray();
				arrayProfessores.add(listProfessores.getSelectedValue());
				ModelListProfessor modelProfessor = new ModelListProfessor(arrayProfessores);
				listProfessoresVinculados.setModel(modelProfessor);
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
	
	public class ModelListProfessor extends AbstractListModel<Professor> {

		private static final long serialVersionUID = -4308246899702883106L;

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
