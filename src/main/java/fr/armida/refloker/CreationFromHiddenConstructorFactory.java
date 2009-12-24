package fr.armida.refloker;

/* package-private */final class CreationFromHiddenConstructorFactory implements ObjectCreationFactory {
	/**
	 * unique instance.
	 */
	public static final ObjectCreationFactory INSTANCE = new CreationFromHiddenConstructorFactory();

	/**
	 * hidden constructor to enforce singleton access.
	 */
	private CreationFromHiddenConstructorFactory() {
		super();
	}

	public <NEW_OBJECT> ObjectCreation<NEW_OBJECT> createObjectCreation(Class<NEW_OBJECT> classOfObjectToCreate) {
		return new HiddenObjectCreation<NEW_OBJECT>(classOfObjectToCreate);
	}

}
