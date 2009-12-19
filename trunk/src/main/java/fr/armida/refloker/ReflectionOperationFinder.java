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

import static fr.armida.refloker.util.AssertNotNull.assertArgumentIsNotNull;
import fr.armida.refloker.util.NullArgumentException;

/*package-private*/abstract class ReflectionOperationFinder<OPERATION_OWNER> {
	/**
	 * For internal use by subclasses.
	 */
	protected final String operationName;
	/**
	 * For internal use by subclasses.
	 */
	private Class<? super OPERATION_OWNER> classWhereOperationIsDeclared;

	protected ReflectionOperationFinder(Class<? super OPERATION_OWNER> classDefiningOperation, String operationName) {
		super();
		this.operationName = operationName;
		setClassWhereOperationIsDeclared(classDefiningOperation);
	}

	public final void setClassWhereOperationIsDeclared(Class<? super OPERATION_OWNER> classDeclaringOperation) throws NullArgumentException {
		assertArgumentIsNotNull(classDeclaringOperation, "classDeclaringOperation");

		classWhereOperationIsDeclared = classDeclaringOperation;
	}

	public final Class<? super OPERATION_OWNER> getClassWhereOperationIsDeclared() {
		return classWhereOperationIsDeclared;
	}

}
