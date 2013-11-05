package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import br.com.senac.controller.ExportarAlunos;
import br.com.senac.dao.AlunoDAO;
import br.com.senac.model.Aluno;

public class ListaAluno extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 3081662216432237545L;
	private JButton btnNovo, btnExportar, btnFechar;
	private JTable tableAlunos;
	private AlunoDAO alunoDAO;
	
	public ListaAluno () {
		alunoDAO = new AlunoDAO();
		initUI();
	}
	
	private final void initUI() {
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Lista de alunos");
		title.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		topPanel.add(title);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/aluno48x48.png"));
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.WEST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        JPanel fieldPanel = new JPanel();
               
        tableAlunos = new JTable(new AlunoModelTable(alunoDAO.listar()));
        tableAlunos.addMouseListener(this);
        fieldPanel.add(tableAlunos);

        JScrollPane pane = new JScrollPane(tableAlunos);
        tableAlunos.setFillsViewportHeight(true);
        pane.setPreferredSize(new Dimension(500, 250));
        fieldPanel.add(pane);
      
        
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnNovo = new JButton("Novo");
        btnNovo.setMnemonic(KeyEvent.VK_N);
        btnNovo.addActionListener(this);
        bottom.add(btnNovo);
        
        btnExportar = new JButton("Exportar");
        btnExportar.setMnemonic(KeyEvent.VK_E);
        btnExportar.addActionListener(this);
        bottom.add(btnExportar);

        btnFechar = new JButton("Fechar");
        btnFechar.setMnemonic(KeyEvent.VK_F);
        btnFechar.addActionListener(this);
        bottom.add(btnFechar);
        
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));

        setTitle("Aluno");
        setSize(new Dimension(600, 400));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNovo) {
            EditarAluno tea = new EditarAluno(null);
            tea.setVisible(true);
            dispose();
		}
		if (e.getSource() == btnFechar) {
			dispose();
		}
		if (e.getSource() == btnExportar) {
			ExportarAlunos exportarAlunos = new ExportarAlunos();

			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Coma Separated Values", "csv");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	exportarAlunos.exportar(alunoDAO.listar(), chooser.getSelectedFile().getAbsolutePath());
		    	JOptionPane.showMessageDialog(null, "Arquivo "
		    			+ chooser.getSelectedFile().getName() + " exportado!");
		    }
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable)e.getSource();
			int row = target.getSelectedRow();
			Aluno a = alunoDAO.getById((Integer) target.getValueAt(row, 0));
            EditarAluno tea = new EditarAluno(a);
            tea.setVisible(true);
            dispose();
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
	
	class AlunoModelTable extends AbstractTableModel {

		private static final long serialVersionUID = 7248039800690616839L;
		
		private ArrayList<Aluno> dados;
		private String[] colunas = {"ID", "Nome", "Sobrenome", "E-mail", "Matricula", "Bolsa"};
		
		public AlunoModelTable (ArrayList<Aluno> dados) {
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
	    		return dados.get(row).getSobrenome();

	    	case 3:
	    		return dados.get(row).getEmail();
	    		
	    	case 4:
	    		return ""; //dados.get(row).getMatricula();
	    		
	    	case 5:
	    		return dados.get(row).getBolsa();
	    		
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

	    	case 2:
	    		if (value instanceof String) {
	    			dados.get(row).setSobrenome((String) value);
	    		}

	    	case 3:
	    		if (value instanceof String) {
	    			dados.get(row).setEmail((String) value);
	    		}
	    		
	    	case 4:
	    		if (value instanceof String) {
	    			//dados.get(row).setMatricula((Integer) value);
	    		}
	    		
	    	case 5:
	    		if (value instanceof Double) {
	    			dados.get(row).setBolsa((Boolean) value);
	    		}
	    		
	    	}
	        fireTableCellUpdated(row, col);
	    }
	    
	}
	
}
