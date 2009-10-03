package fr.armida.refloker;

import java.lang.reflect.Field;

import fr.armida.refloker.util.NullArgumentException;

public class FieldRead<TARGET> {
	private final TARGET target;
	protected final FieldFinder<TARGET> fieldFinder;

	protected FieldRead(TARGET target, String fieldName) {
		this.target = target;
		fieldFinder = FieldFinder.createFinderForFieldOfObject(fieldName, target);
	}

	public final FieldRead<TARGET> definedIn(
			Class<? super TARGET> classDefiningField)
			throws NullArgumentException {
		fieldFinder.definedIn(classDefiningField);
		return this;
	}

	protected final Object readField() {
		Object result;

		try {
			result = doReadField();

		} catch (NoSuchFieldException e) {
			throw new WrongReflectionOperationException(e);
		} catch (IllegalAccessException e) {
			throw new WrongReflectionOperationException(e);
		}

		return result;
	}

	private Object doReadField() throws NoSuchFieldException,
			IllegalAccessException {
		Field field = getReadableField();
		Object result = field.get(target);

		return result;
	}

	protected Field getReadableField() throws NoSuchFieldException {
		return fieldFinder.getFieldFromPublicApi();
	}
}
