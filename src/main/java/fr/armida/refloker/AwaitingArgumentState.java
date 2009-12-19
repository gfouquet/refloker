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

public interface AwaitingArgumentState extends ExecutableQueryState {
	/**
	 * Specifies an argument for a method / constructor. This method assumes the
	 * signature type is the argument's <strong>concrete class</strong>
	 * 
	 * @param <ARG>
	 * @param arg
	 *            arg can be <code>null</code>
	 * @return
	 */
	<ARG> AwaitingArgumentState withArg(ARG arg);

	/**
	 * Specifies an argument for a method / constructor with the expected
	 * signature type.
	 * 
	 * @param <ARG>
	 * @param arg
	 *            arg can be <code>null</code>
	 * @param signatureType
	 *            signatureType can not be <code>null</code>
	 * @return
	 */
	<ARG> AwaitingArgumentState withArg(ARG arg, Class<? super ARG> signatureType);

	/**
	 * For syntactic purpose, same as {@link #withArg(Object)}
	 * 
	 * @see AwaitingArgumentState#withArg(Object)
	 * @param <ARG>
	 * @param arg
	 * @return
	 */
	<ARG> AwaitingArgumentState andArg(ARG arg);

	/**
	 * For syntactic purpose, same as {@link #withArg(Object, Class)}
	 * 
	 * @see AwaitingArgumentState#withArg(Object, Class)
	 * @param <ARG>
	 * @param arg
	 *            arg can be <code>null</code>
	 * @param signatureType
	 *            signatureType can not be <code>null</code>
	 * @return
	 */
	<ARG> AwaitingArgumentState andArg(ARG arg, Class<? super ARG> signatureType);
}
