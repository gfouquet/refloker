package fr.armida.refloker;

import java.lang.reflect.Constructor;

class ConstructorFinder<NEW_OBJECT> {
	private final Class<NEW_OBJECT> classToSearch;

	protected ConstructorFinder(Class<NEW_OBJECT> classToSearch) {
		super();
		assert classToSearch != null;
		this.classToSearch = classToSearch;
	}

	public Constructor<NEW_OBJECT> getConstructorFromPublicApi(Class<?>... signatureTypes) throws NoSuchMethodException {
		return classToSearch.getConstructor(signatureTypes);
	}
}
