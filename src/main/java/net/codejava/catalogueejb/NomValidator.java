package net.codejava.catalogueejb;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("nomValidator")
public class NomValidator implements Validator<String > {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || value.trim().length() == 0) {
            return;
        }
        if (!value.matches("[a-zA-Z]+")) {
            FacesMessage msg = new FacesMessage("les characteres speciaux ne sont pas autorises");
            throw new ValidatorException(msg);
        }

    }
}
