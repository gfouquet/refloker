/**
 *  This file is part of the Refloker library, a small DSL for reflective invocation
 *  Copyright (C) 2009  Gr�gory Fouquet
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

import java.lang.reflect.InvocationTargetException;

public class ExceptionInReflectionTargetException extends RuntimeException {

	private static final long serialVersionUID = 8671296922309059131L;

	protected ExceptionInReflectionTargetException(Throwable cause) {
		super(cause);
	}

	protected static void unwrapCauseAndRethrow(InvocationTargetException e) throws ExceptionInReflectionTargetException {
		Throwable cause = e.getCause();
		throw new ExceptionInReflectionTargetException(cause);
	}

}
