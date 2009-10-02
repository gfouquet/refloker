package fr.armida.refloker;

import static fr.armida.refloker.util.AssertNotNull.assertArgumentIsNotNull;
import fr.armida.refloker.util.NullArgumentException;

/**
 * Entry point for the reflection fluent API, which is meant to be used as
 * follow with static imports. First, define a reflection operation :
 * <code>ReflectionCommand command = on(foo).setHiddenField("bar").to(baz);</code>
 * then execute the operation : <code>execute(command);</code>. These two
 * satements can be regrouped to achieve more "fluence" :
 * <code>execute(on(foo).setHiddenField("bar").to(baz));</code>.
 * 
 * @author Grégory
 * 
 */
public final class Reflector {
	private Reflector() {
		super();
	}

	/**
	 * Starts the definition of a field / method operation by reflection.
	 * 
	 * @param <T>
	 * @param target
	 * @return
	 * @throws NullArgumentException
	 */
	public static <T> ReflectionTarget<T> on(T target)
			throws NullArgumentException {
		assertArgumentIsNotNull(target, "target");

		return new ReflectionTarget<T>(target);
	}

	public static Object executeAndReturnValue(FieldRead<?> fieldRead) {
		return fieldRead.readField();
	}

	public static void execute(FieldSet<?> fieldSet) {
		fieldSet.setField();
	}
}
