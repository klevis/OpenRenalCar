package ramo.klevis.openrental.utils;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.fieldassist.ControlDecoration;

public class MyIntegerValidator implements IValidator {

	private final String errorText;
	private final ControlDecoration controlDecoration;

	public MyIntegerValidator(String errorText,
			ControlDecoration controlDecoration) {
		super();
		this.errorText = errorText;
		this.controlDecoration = controlDecoration;
	}

	public IStatus validate(Object value) {

		
		if (value instanceof Integer) {

			System.out.println("Ok erdhem");
		} else {
			// controlDecoration.show();
			System.out.println(errorText);
			return ValidationStatus.error(errorText);
		}
		// controlDecoration.hide();
		return Status.OK_STATUS;
	}
}
