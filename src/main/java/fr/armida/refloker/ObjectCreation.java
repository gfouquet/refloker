package fr.armida.refloker;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class ObjectCreation<NEW_OBJECT> extends OperationWithArguments<NEW_OBJECT> implements ExecutableQueryState<NEW_OBJECT> {
	private final Class<NEW_OBJECT> newObjectClass;

	private final ConstructorFinder<NEW_OBJECT> constructorFinder;

	protected ObjectCreation(Class<NEW_OBJECT> newObjectClass) {
		super();
		assert newObjectClass != null;
		this.newObjectClass = newObjectClass;
		constructorFinder = new ConstructorFinder<NEW_OBJECT>(newObjectClass);
	}

	public NEW_OBJECT executeAndReturnValue() {
		NEW_OBJECT newObect = null;
		try {
			newObect = doCreateNewObject();
		} catch (NoSuchMethodException e) {
			throw new WrongReflectionOperationException(e);
		} catch (InstantiationException e) {
			throw new WrongReflectionOperationException(e);
		} catch (InvocationTargetException e) {
			ExceptionInReflectionTargetException.unwrapCauseAndRethrow(e);
		} catch (IllegalAccessException e) {
			throw new WrongReflectionOperationException(e);
		}
		return newObect;
	}

	private NEW_OBJECT doCreateNewObject() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		NEW_OBJECT newObect;
		Constructor<NEW_OBJECT> constructor = findConstructorWithArgsOfTypes(getArgsTypes());
		newObect = constructor.newInstance(getArgsValues());
		return newObect;
	}

	private Constructor<NEW_OBJECT> findConstructorWithArgsOfTypes(Class<?>[] argsTypes) throws NoSuchMethodException {
		return constructorFinder.getConstructorFromPublicApi(argsTypes);
	}

}
