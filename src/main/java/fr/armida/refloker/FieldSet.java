/**
 *  This file is part of the Refloker library, a small DSL for reflective invocation
 *  Copyright (C) 2009  Grégory Fouquet
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.armida.refloker;

import java.lang.reflect.Field;


class FieldSet<TARGET> implements AwaitingValueState {
	private TARGET target;
	protected final FieldFinder<TARGET> fieldFinder;
	private Object valueToSet;

	protected FieldSet(TARGET target, String fieldName) {
		this.target = target;
		fieldFinder = FieldFinder.createFinderForFieldOfObject(fieldName, target);
	}

	public final ExecutableCommandState to(Object value) {
		valueToSet = value;
		return new ExecutableCommandFieldSetAdapter<TARGET>(this);
	}

	protected final void setField() {
		try {
			doSetField();
		} catch (NoSuchFieldException e) {
			throw new WrongReflectionOperationException(e);
		} catch (IllegalAccessException e) {
			throw new WrongReflectionOperationException(e);
		}
	}

	private void doSetField() throws NoSuchFieldException, IllegalAccessException {
		Field field = getSettableField();
		
		field.set(target, valueToSet);
	}

	protected Field getSettableField() throws NoSuchFieldException {
		return fieldFinder.getFieldFromPublicApi();
	}

	protected final FieldSet<TARGET> declaredIn(
			Class<? super TARGET> classDeclaringField) {
		fieldFinder.declaredIn(classDeclaringField);
		return this;
	}

}
