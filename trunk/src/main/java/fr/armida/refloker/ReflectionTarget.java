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

import fr.armida.refloker.util.AssertNotNull;


public final class ReflectionTarget<TARGET> implements SelectingOperationState<TARGET> {
	private final TARGET target;

	protected ReflectionTarget(TARGET target) {
		this.target = target;
	}

	/**
	 * Defines the read of a public field.
	 * 
	 * @param fieldName
	 * @return
	 */
	public ExecutableQueryState getField(String fieldName) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");
		return new FieldRead<TARGET>(target, fieldName);
	}

	public ExecutableQueryState getField(String fieldName, Class<? super TARGET> classDeclaringField) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");
		AssertNotNull.assertArgumentIsNotNull(classDeclaringField, "classDeclaringField");

		FieldRead<TARGET> fieldRead = new FieldRead<TARGET>(target, fieldName);
		fieldRead.declaredIn(classDeclaringField);

		return fieldRead;
	}

	/**
	 * Defines the read of a restricted access (protected or less) field.
	 * 
	 * @param fieldName
	 * @return
	 */
	public ExecutableQueryState getHiddenField(String fieldName) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");
		return new HiddenFieldRead<TARGET>(target, fieldName);
	}

	public ExecutableQueryState getHiddenField(String fieldName, Class<? super TARGET> classDeclaringField) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");
		AssertNotNull.assertArgumentIsNotNull(classDeclaringField, "classDeclaringField");

		FieldRead<TARGET> fieldRead = new HiddenFieldRead<TARGET>(target, fieldName);
		fieldRead.declaredIn(classDeclaringField);

		return fieldRead;
	}

	public AwaitingValueState setField(String fieldName) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");
		return new FieldSet<TARGET>(target, fieldName);
	}

	public AwaitingValueState setField(String fieldName, Class<? super TARGET> classDeclaringField) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");
		AssertNotNull.assertArgumentIsNotNull(classDeclaringField, "classDeclaringField");

		FieldSet<TARGET> fieldSet = new FieldSet<TARGET>(target, fieldName);
		fieldSet.declaredIn(classDeclaringField);

		return fieldSet;
	}

	public AwaitingValueState setHiddenField(String fieldName) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");

		return new HiddenFieldSet<TARGET>(target, fieldName);
	}

	public AwaitingValueState setHiddenField(String fieldName, Class<? super TARGET> classDeclaringField) {
		AssertNotNull.assertArgumentIsNotNull(fieldName, "fieldName");
		AssertNotNull.assertArgumentIsNotNull(classDeclaringField, "classDeclaringField");

		FieldSet<TARGET> fieldSet = new HiddenFieldSet<TARGET>(target, fieldName);
		fieldSet.declaredIn(classDeclaringField);

		return fieldSet;
	}

	public AwaitingArgumentState invokeMethod(String methodName) {
		AssertNotNull.assertArgumentIsNotNull(methodName, "methodName");

		return new MethodInvocation<TARGET>(target, methodName);
	}

	public AwaitingArgumentState invokeMethod(String methodName, Class<? super TARGET> classDeclaringMethod) {
		AssertNotNull.assertArgumentIsNotNull(methodName, "methodName");
		AssertNotNull.assertArgumentIsNotNull(classDeclaringMethod, "classDeclaringMethod");

		MethodInvocation<TARGET> invocation = new MethodInvocation<TARGET>(target, methodName);
		invocation.declaredIn(classDeclaringMethod);

		return invocation;
	}

	public AwaitingArgumentState invokeHiddenMethod(String methodName) {
		AssertNotNull.assertArgumentIsNotNull(methodName, "methodName");
		return new HiddenMethodInvocation<TARGET>(target, methodName);
	}

	public AwaitingArgumentState invokeHiddenMethod(String methodName, Class<? super TARGET> classDeclaringMethod) {
		AssertNotNull.assertArgumentIsNotNull(methodName, "methodName");
		AssertNotNull.assertArgumentIsNotNull(classDeclaringMethod, "classDeclaringMethod");

		MethodInvocation<TARGET> invocation = new HiddenMethodInvocation<TARGET>(target, methodName);
		invocation.declaredIn(classDeclaringMethod);

		return invocation;
	}

}
