package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Turma;

public class ListaTurmaProfessor extends JFrame implements ActionListener{

	private static final long serialVersionUID = 5849257271905351845L;
	
	private ImageIcon favicon;
	private JButton btnAdicionar, btnFechar;
	private JTable tableTurmas;
	private TurmaDAO turmaDAO;
	
	public ListaTurmaProfessor () {
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
		JLabel title = new JLabel("Lista de turmas");
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
               
        tableTurmas = new JTable(new TurmaModelTable(turmaDAO.listar()));
        fieldPanel.add(tableTurmas);

        JScrollPane pane = new JScrollPane(tableTurmas);
        tableTurmas.setFillsViewportHeight(true);
        pane.setPreferredSize(new Dimension(500, 250));
        fieldPanel.add(pane);
      
        
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setToolTipText("Adicionar alunos a uma turma.");
        btnAdicionar.setMnemonic(KeyEvent.VK_A);
        btnAdicionar.addActionListener(this);
        bottom.add(btnAdicionar);

        btnFechar = new JButton("Fechar");
        btnFechar.setMnemonic(KeyEvent.VK_F);
        btnFechar.addActionListener(this);
        bottom.add(btnFechar);
        
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));

        setTitle("Definir Professores");
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
		
		if (e.getSource() == btnAdicionar) {
			int row = tableTurmas.getSelectedRow();
			
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Selecione uma turma");
			} else {
				Turma t = turmaDAO.getById((Integer) tableTurmas.getValueAt(row, 0));
				AdicionarProfessorTurma apt = new AdicionarProfessorTurma(t);
				apt.setVisible(true);
				dispose();
			}
		}
	}
	
	class TurmaModelTable extends AbstractTableModel {

		private static final long serialVersionUID = -4097626378869023301L;
		
		private List<Turma> dados;
		private String[] colunas = {"ID", "Nome", "Data Inicio", "Previsão"};
		
		public TurmaModelTable (List<Turma> dados) {
			this.dados = dados;
		}
		
	    public int getColumnCount() {
	        return colunas.length;
	    }

	    public int getRowCount() {
	        return dados.size();
	    }

	    public String getColumnName(int col) {
	        return colunas[col];
	    }

	    public Object getValueAt(int row, int col) {
	    	switch(col) 
	    	{
	    	case 0:
	    		return dados.get(row).getId();
	    		
	    	case 1:
	    		return dados.get(row).getNome();
	    		
	    	case 2:
	    		return dados.get(row).getDataInicio();
	    		
	    	case 3:
	    		return dados.get(row).getPrevisaoTermino();
	    		
	    	default:
	    		return null;
	    	}
	    }

	    public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }

	    /*
	     * Don't need to implement this method unless your table's
	     * editable.
	     */
	    public boolean isCellEditable(int row, int col) {
	        //Note that the data/cell address is constant,
	        //no matter where the cell appears on screen.
	        if (col < 2) {
	            return false;
	        } else {
	            return true;
	        }
	    }

	    /*
	     * Don't need to implement this method unless your table's
	     * data can change.
	     */
	    public void setValueAt(Object value, int row, int col) {
	    	switch(col) 
	    	{
	    	case 0:
	    		if (value instanceof Integer) {
	    			dados.get(row).setId((Integer) value);
	    		}
	    		
	    	case 1:
	    		if (value instanceof String) {
	    			dados.get(row).setNome((String) value);
	    		}

	    	}
	        fireTableCellUpdated(row, col);
	    }
	    
	}
	
}
