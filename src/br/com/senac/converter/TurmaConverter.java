package br.com.senac.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Curso;
import br.com.senac.model.Turma;

@FacesConverter(value = "turmaConverter")
public class TurmaConverter implements Converter {

	@EJB
	private TurmaDAO dao;

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.equals("")) {
            return dao.getById(Integer.parseInt(value)); 
        } else {  
            return null;  
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		try {  
			if (object instanceof Curso) {
				Turma turma = (Turma) object;
				return String.valueOf(turma.getId()); 
			}
        } catch (ConverterException e) {  
            e.printStackTrace();  
        }  
        return null; 
	}

}