/**
 * This file is part of the Refloker library, a small DSL for reflective invocation
 * Copyright (C) 2009  Gr�gory Fouquet
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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
 * @author Gr�gory
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

	public static Object executeAndReturnValue(ExecutableQueryState reflexiveQuery) {
		return reflexiveQuery.executeAndReturnValue();
	}

	public static void execute(ExecutableCommandState reflexiveCommand) {
		reflexiveCommand.execute();
	}

	public static void execute(ExecutableQueryState reflexiveQuery) {
		reflexiveQuery.executeAndReturnValue();
	}

	public static Object executeAndReturnValue(MethodInvocation<?> methodInvocation) {
		return methodInvocation.invokeMethodAndReturnResult();
	}

	/**
	 * Helper method used to specify the superclass where some method / field is
	 * declared.
	 * 
	 * @param <T>
	 * @param definingSuperclass
	 * @return
	 */
	public static <T> Class<T> declaredIn(Class<T> declaringSuperclass) {
		// pass through method, for semantic purpose.
		return declaringSuperclass;
	}

	/**
	 * Helper method used to specify the signature type of some method /
	 * constructor argument.
	 * 
	 * @param <T>
	 * @param definingSuperclass
	 * @return
	 */
	public static <T> Class<T> ofType(Class<T> signatureType) {
		// pass through method, for semantic purpose.
		return signatureType;
	}
}
