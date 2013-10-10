package br.com.senac.view.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import br.com.senac.dao.ProfessorDAO;
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
               
        ProfessorDAO profDAO = new ProfessorDAO();
        
        JTable tableProfessores = new JTable(new ProfessorModelTable(profDAO.listar()));
        fieldPanel.add(tableProfessores);

        JScrollPane pane = new JScrollPane(tableProfessores);
        tableProfessores.setFillsViewportHeight(true);
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
	
	class ProfessorModelTable extends AbstractTableModel {

		private static final long serialVersionUID = -4097626378869023301L;
		
		private ArrayList<Professor> dados;
		private String[] colunas = {"Nome", "Sobrenome", "E-mail", "Especialidade", "Salario", "Vinculo"};
		
		public ProfessorModelTable (ArrayList<Professor> dados) {
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
	    		return dados.get(row).getNome();

	    	case 1:
	    		return dados.get(row).getSobrenome();

	    	case 2:
	    		return dados.get(row).getEmail();
	    		
	    	case 3:
	    		return dados.get(row).getEspecialidade();
	    		
	    	case 4:
	    		return dados.get(row).getSalario();
	    		
	    	case 5:
	    		return dados.get(row).getVinculo();
	    		
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
	    		if (value instanceof String) {
	    			dados.get(row).setNome((String) value);
	    		}

	    	case 1:
	    		if (value instanceof String) {
	    			dados.get(row).setSobrenome((String) value);
	    		}

	    	case 2:
	    		if (value instanceof String) {
	    			dados.get(row).setEmail((String) value);
	    		}
	    		
	    	case 3:
	    		if (value instanceof String) {
	    			dados.get(row).setEspecialidade((String) value);
	    		}
	    		
	    	case 4:
	    		if (value instanceof Double) {
	    			dados.get(row).setSalario((Double) value);
	    		}
	    		
	    	case 5:
	    		if (value instanceof String) {
	    			dados.get(row).setVinculo((String) value);
	    		}	    		
	    	}
	        fireTableCellUpdated(row, col);
	    }
	    
	}
	
}
