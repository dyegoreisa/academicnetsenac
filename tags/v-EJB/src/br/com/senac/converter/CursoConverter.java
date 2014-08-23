package br.com.senac.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.senac.dao.CursoDAO;
import br.com.senac.model.Curso;

@FacesConverter(value = "cursoConverter")
public class CursoConverter implements Converter {

	@EJB
	private CursoDAO dao;
	
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
				Curso curso = (Curso) object;
				return String.valueOf(curso.getId()); 
			}
        } catch (ConverterException e) {  
            e.printStackTrace();  
        }  
        return null; 
	}

}