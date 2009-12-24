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
 * This class is responsible for looking up constructors for objects of
 * parameterized type.
 * 
 * @author Grégory
 * 
 * @param <SEARCHED>
 *            the type of the object which constructors are to find.
 */
/* package-private */class ConstructorFinder<SEARCHED> {
	/**
	 * the class to search i.e. the class which constructor we are going to look
	 * up.
	 */
	private final Class<SEARCHED> classToSearch;

	protected ConstructorFinder(Class<SEARCHED> classToSearch) {
		super();
		assert classToSearch != null;
		this.classToSearch = classToSearch;
	}

	public Constructor<SEARCHED> getConstructorFromPublicApi(Class<?>... signatureTypes) throws NoSuchMethodException {
		return classToSearch.getConstructor(signatureTypes);
	}

	public Constructor<SEARCHED> getConstructorRegardlessVisibility(Class<?>... signatureTypes) throws NoSuchMethodException {
		return classToSearch.getDeclaredConstructor(signatureTypes);
	}
}
