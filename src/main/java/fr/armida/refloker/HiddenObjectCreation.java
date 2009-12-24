package fr.armida.refloker;

import java.lang.reflect.Constructor;

/**
 * This class is responsible for creating objects of parameterized type using
 * one of their hidden constructors.
 * 
 * @author Grégory
 * 
 * @param <OBJECT_TO_CREATE>
 *            The type of the object to create.
 */
/* package-private */class HiddenObjectCreation<OBJECT_TO_CREATE> extends ObjectCreation<OBJECT_TO_CREATE> {

	protected HiddenObjectCreation(Class<OBJECT_TO_CREATE> classOfObjectToCreate) {
		super(classOfObjectToCreate);
	}

	@Override
	protected Constructor<OBJECT_TO_CREATE> findConstructorWithArgsOfTypes(Class<?>[] argsTypes) throws NoSuchMethodException {
		Constructor<OBJECT_TO_CREATE> constructor = constructorFinder.getConstructorRegardlessVisibility(argsTypes);
		constructor.setAccessible(true);
		return constructor;
	}

}
