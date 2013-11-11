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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import br.com.senac.dao.CursoDAO;
import br.com.senac.model.Curso;

public class ListaCurso extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 3081662216432237545L;
	
	private ImageIcon favicon;
	private JButton btnNovo, btnFechar;
	private JTable tableCursos;
	private CursoDAO cursoDAO;
	
	public ListaCurso () {
		cursoDAO = new CursoDAO();
		initUI();
	}
	
	private final void initUI() {
		
		favicon = new ImageIcon(getClass().getResource("/images/curso16x16.png"));
		
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);
		
		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel title = new JLabel("Lista de Cursos");
		title.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		topPanel.add(title);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/curso48x48.png"));
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.WEST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        JPanel fieldPanel = new JPanel();
               
        tableCursos = new JTable(new CursoModelTable(cursoDAO.listar()));
        tableCursos.addMouseListener(this);
        fieldPanel.add(tableCursos);

        JScrollPane pane = new JScrollPane(tableCursos);
        tableCursos.setFillsViewportHeight(true);
        pane.setPreferredSize(new Dimension(500, 250));
        fieldPanel.add(pane);
      
        
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basic.add(fieldPanel);      

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnNovo = new JButton("Novo");
        btnNovo.setMnemonic(KeyEvent.VK_N);
        btnNovo.addActionListener(this);
        bottom.add(btnNovo);
        
        btnFechar = new JButton("Fechar");
        btnFechar.setMnemonic(KeyEvent.VK_F);
        btnFechar.addActionListener(this);
        bottom.add(btnFechar);
        
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));

        setTitle("Cursos");
        setIconImage(favicon.getImage());
        setSize(new Dimension(600, 400));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNovo) {
            EditarCurso tela = new EditarCurso(null);
            tela.setVisible(true);
            dispose();
		}
		if (e.getSource() == btnFechar) {
			dispose();
		}		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable)e.getSource();
			int row = target.getSelectedRow();
			Curso curso = cursoDAO.getById((Integer) target.getValueAt(row, 0));
            EditarCurso tea = new EditarCurso(curso);
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
	
	class CursoModelTable extends AbstractTableModel {

		private static final long serialVersionUID = 7248039800690616839L;
		
		private ArrayList<Curso> dados;
		private String[] colunas = {"ID", "Nome"};
		
		public CursoModelTable (ArrayList<Curso> dados) {
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
