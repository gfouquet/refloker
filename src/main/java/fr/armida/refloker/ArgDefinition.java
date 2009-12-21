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

import fr.armida.refloker.util.AssertNotNull;

/*package-private*/final class ArgDefinition<ARG, OPERATION extends OperationWithArguments<?>> {
	private final OPERATION operation;
	private final ARG value;
	private Class<?> signatureType;

	private ArgDefinition(OPERATION operation, ARG value) {
		super();
		this.operation = operation;
		this.value = value;
		signatureType = value.getClass();
	}

	protected ARG getValue() {
		return value;
	}

	protected Class<?> getSignatureType() {
		return signatureType;
	}

	public OPERATION ofType(Class<? super ARG> typeInSignature) {
		this.signatureType = typeInSignature;

		return operation;
	}

	public static <A, O extends OperationWithArguments<?>> ArgDefinition<A, O> createDefinitionForArgOfMethod(A value, O operation) {
		AssertNotNull.assertArgumentIsNotNull(value, "value");
		AssertNotNull.assertArgumentIsNotNull(operation, "operation");

		ArgDefinition<A, O> def = new ArgDefinition<A, O>(operation,
				value);
		return def;
	}
}
