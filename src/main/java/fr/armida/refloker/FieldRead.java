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

import java.lang.reflect.Field;

import fr.armida.refloker.util.NullArgumentException;

public class FieldRead<TARGET> implements ExecutableQueryState {
	private final TARGET target;
	protected final FieldFinder<TARGET> fieldFinder;

	protected FieldRead(TARGET target, String fieldName) {
		this.target = target;
		fieldFinder = FieldFinder.createFinderForFieldOfObject(fieldName, target);
	}

	protected final FieldRead<TARGET> declaredIn(
			Class<? super TARGET> classDeclaringField)
			throws NullArgumentException {
		fieldFinder.declaredIn(classDeclaringField);
		return this;
	}

	protected final Object readField() {
		Object result;

		try {
			result = doReadField();

		} catch (NoSuchFieldException e) {
			throw new WrongReflectionOperationException(e);
		} catch (IllegalAccessException e) {
			throw new WrongReflectionOperationException(e);
		}

		return result;
	}

	private Object doReadField() throws NoSuchFieldException,
			IllegalAccessException {
		Field field = getReadableField();
		Object result = field.get(target);

		return result;
	}

	protected Field getReadableField() throws NoSuchFieldException {
		return fieldFinder.getFieldFromPublicApi();
	}

	public Object executeAndReturnValue() {
		return readField();
	}
}
