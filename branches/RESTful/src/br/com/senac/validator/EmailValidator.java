package br.com.senac.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validaEmail")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher((String) object);

		if (!m.matches()) {
			((UIInput) component).setValid(false);

			context.addMessage(component.getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Envio de mensagem: ", "Email invalido!"));
		}

	}
}
