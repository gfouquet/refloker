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