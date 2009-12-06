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

/*package-private*/final class FieldFinder<FIELD_OWNER> extends
		ReflectionOperationFinder<FIELD_OWNER> {
	private FieldFinder(Class<? super FIELD_OWNER> classWhereFieldIsDefined,
			String fieldName) {
		super(classWhereFieldIsDefined, fieldName);
	}

	public Field getFieldFromPublicApi() throws NoSuchFieldException {
		return getClassWhereOperationIsDefined().getField(operationName);
	}

	public Field getFieldRegardlessVisibility() throws NoSuchFieldException {
		return getClassWhereOperationIsDefined()
				.getDeclaredField(operationName);
	}

	@SuppressWarnings("unchecked")
	public static <FIELD_OWNER> FieldFinder<FIELD_OWNER> createFinderForFieldOfObject(
			String fieldName, FIELD_OWNER ownerOfField) {
		Class<? super FIELD_OWNER> classWhereFieldIsDefined = (Class<? super FIELD_OWNER>) ownerOfField.getClass();	
		return new FieldFinder<FIELD_OWNER>(classWhereFieldIsDefined, fieldName);

	}
}
