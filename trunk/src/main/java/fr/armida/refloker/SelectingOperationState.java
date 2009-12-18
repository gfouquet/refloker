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

public interface SelectingOperationState<TARGET> {
	/**
	 * Selects an operation of field read.
	 * 
	 * @param fieldName
	 * @return
	 */
	ExecutableQueryState getField(String fieldName);

	/**
	 * Selects an operation of field read. The field is declared in the
	 * specified superclass.
	 * 
	 * @param fieldName
	 * @param declaringSuperclass
	 * @return
	 */
	ExecutableQueryState getField(String fieldName, Class<? super TARGET> declaringSuperclass);

	/**
	 * Selects an operation of hidden field read. Hidden means any non-public
	 * visibility.
	 * 
	 * @param fieldName
	 * @return
	 */
	ExecutableQueryState getHiddenField(String fieldName);

	/**
	 * Selects an operation of field read. The field is declared in the
	 * specified superclass. Hidden means any non-public visibility.
	 * 
	 * @param fieldName
	 * @param declaringSuperclass
	 * @return
	 */
	ExecutableQueryState getHiddenField(String fieldName, Class<? super TARGET> declaringSuperclass);

	/**
	 * Selects an opeteration of field set.
	 * 
	 * @param fieldame
	 * @return
	 */
	AwaitingValueState setField(String fieldName);

	/**
	 * Selects an operation of field set. The field is declared in the
	 * specified superclass.
	 * 
	 * @param fieldame
	 * @return
	 */
	AwaitingValueState setField(String fieldName, Class<? super TARGET> declaringSuperclass);

	/**
	 * Selects an operation of hidden field set. Hidden means any non-public
	 * visibility.
	 * 
	 * @param fieldName
	 * @return
	 */
	AwaitingValueState setHiddenField(String fieldName);

	/**
	 * Selects an operation of hidden set read. The field is declared in the
	 * specified superclass. Hidden means any non-public visibility.
	 * 
	 * @param fieldName
	 * @param declaringSuperclass
	 * @return
	 */
	AwaitingValueState setHiddenField(String fieldName, Class<? super TARGET> declaringSuperclass);

	/**
	 * Selects an operation of method invocation.
	 * 
	 * @param methodName
	 * @return
	 */
	AwaitingArgumentState invokeMethod(String methodName);
	/**
	 * Selects an operation of hidden method read. Hidden means any non-public
	 * visibility.
	 * 
	 * @param methodName
	 * @return
	 */
	AwaitingArgumentState invokeHiddenMethod(String methodName);
}
