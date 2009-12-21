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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.armida.refloker.util.CollectionAdapter;

/* package-private */abstract class OperationWithArguments<TARGET> {

	private final List<ArgDefinition<?, ? extends OperationWithArguments<TARGET>>> argsDefinitions = new ArrayList<ArgDefinition<?, ? extends OperationWithArguments<TARGET>>>();
	private final Collection<Class<?>> argsTypesAdapter;
	private final Collection<?> argsValuesAdapter;

	protected OperationWithArguments() {
		super();
		this.argsTypesAdapter = adaptToSignatureTypes(argsDefinitions);
		this.argsValuesAdapter = adaptToValues(argsDefinitions);
	}

	private Collection<?> adaptToValues(List<ArgDefinition<?, ? extends OperationWithArguments<TARGET>>> argsDefinitions) {
		return new CollectionAdapter<ArgDefinition<?, ? extends OperationWithArguments<TARGET>>, Object>(argsDefinitions) {

			@Override
			protected Object adapt(ArgDefinition<?, ? extends OperationWithArguments<TARGET>> sourceItem) {
				return sourceItem.getValue();
			}
		};
	}

	private Collection<Class<?>> adaptToSignatureTypes(List<ArgDefinition<?, ? extends OperationWithArguments<TARGET>>> argsDefinitions) {
		return new CollectionAdapter<ArgDefinition<?, ? extends OperationWithArguments<TARGET>>, Class<?>>(argsDefinitions) {

			@Override
			protected Class<?> adapt(ArgDefinition<?, ? extends OperationWithArguments<TARGET>> sourceItem) {
				return sourceItem.getSignatureType();
			}
		};
	}

	protected final Class<?>[] getArgsTypes() {
		int nTypes = argsTypesAdapter.size();
		return argsTypesAdapter.toArray(new Class<?>[nTypes]);
	}

	protected final Object[] getArgsValues() {
		return argsValuesAdapter.toArray();
	}
	
	protected final <ARG> void addArgDefinition(ArgDefinition<ARG, ? extends OperationWithArguments<TARGET>> argDef) {
		assert argDef != null;
		argsDefinitions.add(argDef);
	}

}