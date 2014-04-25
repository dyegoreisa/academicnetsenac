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
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Aluno;
import br.com.senac.model.Turma;

public class AdicionarAlunoTurma extends JFrame implements ActionListener, MouseListener  {

	private static final long serialVersionUID = 714672937069102260L;
	
	private ImageIcon favicon;
	private Turma turma;
	private AlunoDAO alunoDAO;
	private TurmaDAO turmaDAO;
	private int id;
	private JButton btnSalvar, btnFechar;
	private JList<Aluno> listAlunos, listAlunosMatriculados;
	
	public AdicionarAlunoTurma (Turma turma) {
        if (turma == null) {
        	JOptionPane.showMessageDialog(this, "Deve ser selecionada uma turma.");
        	dispose();
        } else {
        	this.turma = turma;
        }

        alunoDAO = new AlunoDAO();
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
		JLabel title = new JLabel("Adicionar alunos a Turma");
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
        JLabel lblListaAlunos = new JLabel("Lista de Alunos:");
        lblListaAlunos.setFont(fontLabel);
        lblListaAlunos.setForeground(colorLabel);
        labelPanel.add(lblListaAlunos, BorderLayout.WEST);
        
        // Labels Lista de Alunos Matriculados
        JLabel lblListaAlunoMatriculados = new JLabel("Lista de Alunos Matriculados:");
        lblListaAlunoMatriculados.setFont(fontLabel);
        lblListaAlunoMatriculados.setForeground(colorLabel);
        labelPanel.add(lblListaAlunoMatriculados, BorderLayout.EAST);

        labelPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(labelPanel);      

        
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1, 2));
        
        // Lista de alunos
        listAlunos = new JList<Aluno>(new ModelListAluno(alunoDAO.listar()));
        listAlunos.addMouseListener(this);
        JScrollPane paneListaAlunos = new JScrollPane();
        paneListaAlunos.getViewport().add(listAlunos);
        fieldPanel.add(paneListaAlunos, BorderLayout.WEST);
        
        // Lista de alunos Matriculados
        listAlunosMatriculados = new JList<Aluno>(new ModelListAluno(turmaDAO.listarAlunos(turma)));
        listAlunosMatriculados.addMouseListener(this);
        JScrollPane paneListaAlunosMatriculados = new JScrollPane();
        paneListaAlunosMatriculados.getViewport().add(listAlunosMatriculados);
        fieldPanel.add(paneListaAlunosMatriculados, BorderLayout.EAST);
        
        		
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
        
        setTitle("Adicionar alunos à Turma");
        setIconImage(favicon.getImage());
        setSize(new Dimension(550, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			ModelListAluno modelListAlu = (ModelListAluno) listAlunosMatriculados.getModel();
			List<Aluno> arrayAlunos = modelListAlu.getArray();
			
			turmaDAO.atualizar(turma);
			
			ListaTurmaMatricula tltm = new ListaTurmaMatricula();
			tltm.setVisible(true);
			
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
			if (e.getSource() == listAlunos) {
				ModelListAluno modelListAlu = (ModelListAluno) listAlunosMatriculados.getModel();
				List<Aluno> arrayAlunos = modelListAlu.getArray();
				
				Aluno aluno = listAlunos.getSelectedValue();
	
				turma.addAluno(aluno);
				
				arrayAlunos.add(aluno);
				
				ModelListAluno modelAlunos = new ModelListAluno(arrayAlunos);
				listAlunosMatriculados.setModel(modelAlunos);
			}
			
			if (e.getSource() == listAlunosMatriculados) {
				int index = listAlunosMatriculados.getSelectedIndex();
				listAlunosMatriculados.remove(index);
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

}
