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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.armida.refloker.util.AssertNotNull;
import fr.armida.refloker.util.CollectionAdapter;

public class MethodInvocation<TARGET> implements AwaitingArgumentState {
	private final TARGET target;
	protected final MethodFinder<TARGET> methodFinder;

	private final List<ArgDefinition<?, ? extends MethodInvocation<TARGET>>> argsDefinitions = new ArrayList<ArgDefinition<?, ? extends MethodInvocation<TARGET>>>();
	private final Collection<Class<?>> argsTypesAdapter;
	private final Collection<?> argsValuesAdapter;

	protected MethodInvocation(TARGET target, String methodName) {
		this.target = target;
		methodFinder = MethodFinder.createFinderForMethodOfObject(methodName, target);

		this.argsTypesAdapter = adaptToSignatureTypes(argsDefinitions);
		this.argsValuesAdapter = adaptToValues(argsDefinitions);
	}

	private Collection<?> adaptToValues(List<ArgDefinition<?, ? extends MethodInvocation<TARGET>>> argsDefinitions) {
		return new CollectionAdapter<ArgDefinition<?, ? extends MethodInvocation<TARGET>>, Object>(argsDefinitions) {

			@Override
			protected Object adapt(ArgDefinition<?, ? extends MethodInvocation<TARGET>> sourceItem) {
				return sourceItem.getValue();
			}
		};
	}

	private Collection<Class<?>> adaptToSignatureTypes(List<ArgDefinition<?, ? extends MethodInvocation<TARGET>>> argsDefinitions) {
		return new CollectionAdapter<ArgDefinition<?, ? extends MethodInvocation<TARGET>>, Class<?>>(argsDefinitions) {

			@Override
			protected Class<?> adapt(ArgDefinition<?, ? extends MethodInvocation<TARGET>> sourceItem) {
				return sourceItem.getSignatureType();
			}
		};
	}

	/**
	 * Defines an argument of the method to invoke. /!\ This object will try to
	 * invoke a method with a signature type the concrete type of the passed
	 * argument. If the signature type is not the concrete type of argument,
	 * signature type should be indicated through
	 * <code>withArg(value, ofType(signatureType))</code>
	 * 
	 * @see ArgDefinition#ofType(Class)
	 * 
	 * @param <ARG>
	 * @param arg
	 * @return
	 */
	public/* final */<ARG> AwaitingArgumentState withArg(ARG arg) {
		ArgDefinition<ARG, MethodInvocation<TARGET>> definition = ArgDefinition.createDefinitionForArgOfMethod(arg, this);
		argsDefinitions.add(definition);
		return this;
	}

	/**
	 * Syntactic sugar, same as {@link #withArg(Object)}
	 * 
	 * @param arg
	 * @return
	 */
	public/* final */<ARG> AwaitingArgumentState andArg(ARG arg) {
		return withArg(arg);
	}

	/**
	 * inner api, should not be used by clients. invokes the method defined by
	 * this object.
	 * 
	 * @throws WrongReflectionOperationException
	 * @throws ExceptionInReflectionTargetException
	 */
	protected/* final */void invokeMethod() throws WrongReflectionOperationException, ExceptionInReflectionTargetException {
		invokeMethodAndReturnResult();
	}

	/**
	 * inner api, should not be used by clients. invokes the method defined by
	 * this object and returns the result of the method.
	 * 
	 * @return
	 */
	protected/* final */Object invokeMethodAndReturnResult() {
		Object result = null;

		try {
			result = doInvokeMethodAndReturnResult();
		} catch (NoSuchMethodException e) {
			throw new WrongReflectionOperationException(e);
		} catch (IllegalAccessException e) {
			throw new WrongReflectionOperationException(e);
		} catch (InvocationTargetException e) {
			unwrapAndRethrowCause(e);
		}

		return result;

	}

	private Object doInvokeMethodAndReturnResult() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method method = findMethodWithArgsOfTypes(getArgsTypes());

		Object[] argsValues = getArgsValues();
		return method.invoke(target, argsValues);

	}

	/**
	 * this method should be overridden by subclasses to specialize how the
	 * method of the target object should be found.
	 * 
	 * @param argsTypes
	 *            types of arguments in method signature.
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 */
	protected Method findMethodWithArgsOfTypes(Class<?>[] argsTypes) throws NoSuchMethodException {
		return methodFinder.getMethodFromPublicApi(argsTypes);
	}

	private Class<?>[] getArgsTypes() {
		int nTypes = argsTypesAdapter.size();
		return argsTypesAdapter.toArray(new Class<?>[nTypes]);
	}

	private Object[] getArgsValues() {
		return argsValuesAdapter.toArray();
	}

	private void unwrapAndRethrowCause(InvocationTargetException e) throws ExceptionInReflectionTargetException {
		Throwable cause = e.getCause();
		throw new ExceptionInReflectionTargetException(cause);
	}

	public Object executeAndReturnValue() {
		return invokeMethodAndReturnResult();
	}

	public <ARG> AwaitingArgumentState withArg(ARG arg, Class<? super ARG> signatureType) {
		AssertNotNull.assertArgumentIsNotNull(signatureType, "signatureType");

		ArgDefinition<ARG, MethodInvocation<TARGET>> definition = ArgDefinition.createDefinitionForArgOfMethod(arg, this);
		definition.ofType(signatureType);
		argsDefinitions.add(definition);

		return this;
	}

	/**
	 * Syntactic sugar, same as {@link #withArg(Object, Class)}
	 */
	public <ARG> AwaitingArgumentState andArg(ARG arg, Class<? super ARG> signatureType) {
		return withArg(arg, signatureType);
	}
}
