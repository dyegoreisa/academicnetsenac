package br.com.senac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.senac.dao.CursoDAO;
import br.com.senac.model.Curso;

@FacesConverter(value = "cursoConverter", forClass = Curso.class)
public class CursoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CursoDAO dao = new CursoDAO();
		if (value != null && value.equals("")) {
			Curso curso = new Curso();
			curso = dao.getById(Integer.parseInt(value));
            return curso;  
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