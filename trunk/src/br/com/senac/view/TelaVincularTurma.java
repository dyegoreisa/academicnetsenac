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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import br.com.senac.dao.AlunoDAO;
import br.com.senac.dao.ProfessorDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Aluno;
import br.com.senac.model.Turma;

public class TelaVincularTurma extends JFrame implements ActionListener  {

	private Turma turma;
	private TurmaDAO turmaDAO;
	private AlunoDAO alunoDAO;
	private ProfessorDAO professorDAO;
	private JTextField txt;
	private JButton btnSalvar, btnFechar;
	private JList listAlunosVinculados;
	private JList listProfessoresVinculados;
	
	public TelaVincularTurma (Turma turma) {
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
        JList listAlunos = new JList(new ModelListAluno(alunoDAO.listar());
        JScrollPane paneListaAlunos = new JScrollPane();
        paneListaAlunos.getViewport().add(listAlunos);
        fieldPanel.add(paneListaAlunos);
        
        // Lista de alunos vinculados
    /*    listAlunosVinculados = new JList();
        JScrollPane paneListaAlunosVinculados = new JScrollPane();
        paneListaAlunos.getViewport().add(listAlunosVinculados);
        fieldPanel.add(paneListaAlunosVinculados);
      */  
        
       /* // Lista de professores
        String[] arrayProfessores = {"ja", "vi", "jo", "ma"};
        JList listProfessores = new JList(arrayProfessores);
        JScrollPane paneListaProfessores = new JScrollPane();
        paneListaProfessores.getViewport().add(listProfessores);
        fieldPanel.add(paneListaProfessores);
        
        // Lista de professores vinculados
        listProfessoresVinculados = new JList(arrayProfessores);
        JScrollPane paneListaProfessoresVinculados = new JScrollPane();
        paneListaProfessores.getViewport().add(listProfessoresVinculados);
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
        setSize(new Dimension(450, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
		}
		
		TelaListaTurma tlt = new TelaListaTurma();
		tlt.setVisible(true);
		
		dispose();
	}
	
	public class ModelListAluno extends AbstractTableModel {

		private static final long serialVersionUID = -6382215151854365504L;

		private ArrayList<Aluno> alunos;
		
		public ModelListAluno(ArrayList<Aluno> alunos) {
			this.alunos = alunos; 
		}
		
		@Override
		public int getRowCount() {
			return alunos.size();
		}

		@Override
		public int getColumnCount() {
			return 9;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex)
			{
			case 0:
				return alunos.get(rowIndex).getId();
				
			case 1:
				return alunos.get(rowIndex).getNome();
				
			case 2:
				return alunos.get(rowIndex).getSobrenome();
				
			case 3:
				return alunos.get(rowIndex).getSexo();
				
			case 4:
				return alunos.get(rowIndex).getTelefone();
				
			case 5:
				SimpleDateFormat formated = new SimpleDateFormat("dd/MM/YYYY");
				return alunos.get(rowIndex).getDataNascimento(formated);
				
			case 6:
				return alunos.get(rowIndex).getEmail();
				
			case 7:
				return alunos.get(rowIndex).getMatricula();
				
			case 8:
				return alunos.get(rowIndex).getBolsa();
				
			default:
				return null;
			}
		}
		
	}
}
