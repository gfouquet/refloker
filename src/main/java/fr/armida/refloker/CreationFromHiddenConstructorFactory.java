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
