package fr.armida.refloker;

/**
 * interface for factories of {@link ObjectCreation} objects.
 * 
 * @author Gr�gory
 * 
 */
public interface ObjectCreationFactory {
	/**
	 * creates an {@link ObjectCreation} object responsible for creating objects
	 * of classOfObjectToCreate class.
	 * 
	 * @param <NEW_OBJECT>
	 * @param classOfObjectToCreate
	 * @return
	 */
	<NEW_OBJECT> ObjectCreation<NEW_OBJECT> createObjectCreation(Class<NEW_OBJECT> classOfObjectToCreate);
}
