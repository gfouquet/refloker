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


public class ReflectionTarget<TARGET> {
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
	public FieldRead<TARGET> getField(String fieldName) {
		return new FieldRead<TARGET>(target, fieldName);
	}

	/**
	 * Defines the read of a restricted access (protected or less) field.
	 * 
	 * @param fieldName
	 * @return
	 */
	public FieldRead<TARGET> getHiddenField(String fieldName) {
		return new HiddenFieldRead<TARGET>(target, fieldName);
	}

	public FieldSet<TARGET> setField(String fieldName) {
		return new FieldSet<TARGET>(target, fieldName);
	}

	public FieldSet<TARGET> setHiddenField(String fieldName) {
		return new HiddenFieldSet<TARGET>(target, fieldName);
	}

	public MethodInvocation<TARGET> invokeMethod(String methodName) {
		return new MethodInvocation<TARGET>(target, methodName);
	}

	public MethodInvocation<TARGET> invokeHiddenMethod(String methodName) {
		return new HiddenMethodInvocation<TARGET>(target, methodName);
	}
}
