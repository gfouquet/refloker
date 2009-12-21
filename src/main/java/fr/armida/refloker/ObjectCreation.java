/**
 * This file is part of the Refloker library, a small DSL for reflective invocation
 * Copyright (C) 2009  Grégory Fouquet
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
