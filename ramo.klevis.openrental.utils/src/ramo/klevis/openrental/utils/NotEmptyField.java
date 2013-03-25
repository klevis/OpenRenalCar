package ramo.klevis.openrental.utils;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.fieldassist.ControlDecoration;

public class NotEmptyField implements IValidator {

	private final String message;
	private final ControlDecoration controlDecoration;

	public NotEmptyField(String message, ControlDecoration controlDecoration) {
		super();
		this.message = message;
		this.controlDecoration = controlDecoration;
	}

	@Override
	public IStatus validate(Object arg0) {
		// TODO Auto-generated method stubSt
		if (arg0 instanceof String) {
			String ar = (String) arg0;

			if (ar.isEmpty()==false) {
				controlDecoration.hide();
				return Status.OK_STATUS;
			} else {

				controlDecoration.show();
				return ValidationStatus.error(message);
			}
		} else {
			throw new RuntimeException(
					"Not supposed to be called for non-strings.");
		}
	}

}
