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

public final class ArgDefinition<ARG, METHOD_INVOCATION extends MethodInvocation<?>> {
	private final METHOD_INVOCATION methodInvocation;
	private final ARG value;
	private Class<?> signatureType;

	private ArgDefinition(METHOD_INVOCATION methodInvocation, ARG value) {
		super();
		this.methodInvocation = methodInvocation;
		this.value = value;
		signatureType = value.getClass();
	}

	protected ARG getValue() {
		return value;
	}

	protected Class<?> getSignatureType() {
		return signatureType;
	}

	public METHOD_INVOCATION ofType(Class<? super ARG> typeInSignature) {
		this.signatureType = typeInSignature;

		return methodInvocation;
	}

	protected static <A, MI extends MethodInvocation<?>> ArgDefinition<A, MI> createDefinitionForArgOfMethod(
			A value, MI methodInvocation) {
		ArgDefinition<A, MI> def = new ArgDefinition<A, MI>(methodInvocation,
				value);
		return def;
	}

	@SuppressWarnings("unchecked")
	public <OTHER_ARG> ArgDefinition<OTHER_ARG, METHOD_INVOCATION> andArg(OTHER_ARG arg) {
        return (ArgDefinition<OTHER_ARG, METHOD_INVOCATION>) methodInvocation.andArg(arg);
    }

	/* package-private */METHOD_INVOCATION getMethodInvocation() {
		return methodInvocation;
	}
}
