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

import java.lang.reflect.Method;

final class MethodFinder<METHOD_OWNER> extends
		ReflectionOperationFinder<METHOD_OWNER> {

	private MethodFinder(Class<? super METHOD_OWNER> classDefiningMethod,
			String methodName) {
		super(classDefiningMethod, methodName);
	}

	public Method getMethodFromPublicApi(Class<?>... signatureTypes)
			throws NoSuchMethodException {
		return getClassWhereOperationIsDeclared().getDeclaredMethod(
				operationName, signatureTypes);
	}

	@SuppressWarnings("unchecked")
	public static <METHOD_OWNER> MethodFinder<METHOD_OWNER> createFinderForMethodOfObject(
			String methodName, METHOD_OWNER ownerOfMethod) {
		Class<? super METHOD_OWNER> classDefiningMethod = (Class<? super METHOD_OWNER>) ownerOfMethod
				.getClass();

		return new MethodFinder<METHOD_OWNER>(classDefiningMethod, methodName);
	}

	public Method getMethodRegardlessVisibility(Class<?>... signatureTypes)
			throws NoSuchMethodException {
		Method method = getClassWhereOperationIsDeclared().getDeclaredMethod(
				operationName, signatureTypes);

		return method;
	}

}
