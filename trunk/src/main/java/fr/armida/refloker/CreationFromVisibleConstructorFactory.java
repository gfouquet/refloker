package fr.armida.refloker;

/**
 * Factory for {@link ObjectCreation} objects that create objects using a
 * visible constructor. Used as a singleton.
 * 
 * @author Grégory
 * 
 */
/* package-private */final class CreationFromVisibleConstructorFactory implements ObjectCreationFactory {
	/**
	 * unique instance.
	 */
	public static final ObjectCreationFactory INSTANCE = new CreationFromVisibleConstructorFactory();

	/**
	 * hidden constructor to enforce singleton access.
	 */
	private CreationFromVisibleConstructorFactory() {
		super();
	}

	public <NEW_OBJECT> ObjectCreation<NEW_OBJECT> createObjectCreation(Class<NEW_OBJECT> classOfObjectToCreate) {
		return new ObjectCreation<NEW_OBJECT>(classOfObjectToCreate);
	}

}
