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

import fr.armida.refloker.util.AssertNotNull;

class ObjectCreation<OBJECT_TO_CREATE> extends OperationWithArguments<OBJECT_TO_CREATE> implements AwaitingArgumentState<OBJECT_TO_CREATE> {
	protected final ConstructorFinder<OBJECT_TO_CREATE> constructorFinder;

	protected ObjectCreation(Class<OBJECT_TO_CREATE> classOfObjectToCreate) {
		super();
		assert classOfObjectToCreate != null;
		constructorFinder = new ConstructorFinder<OBJECT_TO_CREATE>(classOfObjectToCreate);
	}

	public OBJECT_TO_CREATE executeAndReturnValue() {
		OBJECT_TO_CREATE newObect = null;
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

	/**
	 * Template method which creates a new object. Invokes
	 * {@link #findConstructorWithArgsOfTypes(Class[])}
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private OBJECT_TO_CREATE doCreateNewObject() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<OBJECT_TO_CREATE> constructor = findConstructorWithArgsOfTypes(getArgsTypes());

		return constructor.newInstance(getArgsValues());
	}

	/**
	 * This method should be overridden to specialize the
	 * {@link #doCreateNewObject()} template method.
	 * 
	 * @param argsTypes
	 * @return
	 * @throws NoSuchMethodException
	 */
	protected Constructor<OBJECT_TO_CREATE> findConstructorWithArgsOfTypes(Class<?>[] argsTypes) throws NoSuchMethodException {
		return constructorFinder.getConstructorFromPublicApi(argsTypes);
	}

	public <ARG> AwaitingArgumentState<OBJECT_TO_CREATE> andArg(ARG arg) {
		return withArg(arg);
	}

	public <ARG> AwaitingArgumentState<OBJECT_TO_CREATE> andArg(ARG arg, Class<? super ARG> signatureType) {
		return withArg(arg, signatureType);
	}

	public <ARG> AwaitingArgumentState<OBJECT_TO_CREATE> withArg(ARG arg) {
		AssertNotNull.assertArgumentIsNotNull(arg, "arg");

		ArgDefinition<ARG, ObjectCreation<OBJECT_TO_CREATE>> argDef = ArgDefinition.createDefinitionForArgOfMethod(arg, this);
		addArgDefinition(argDef);

		return this;
	}

	public <ARG> AwaitingArgumentState<OBJECT_TO_CREATE> withArg(ARG arg, Class<? super ARG> signatureType) {
		AssertNotNull.assertArgumentIsNotNull(arg, "arg");
		AssertNotNull.assertArgumentIsNotNull(signatureType, "signatureType");

		ArgDefinition<ARG, ObjectCreation<OBJECT_TO_CREATE>> argDef = ArgDefinition.createDefinitionForArgOfMethod(arg, this);
		argDef.ofType(signatureType);
		addArgDefinition(argDef);
		return this;
	}

}
