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
