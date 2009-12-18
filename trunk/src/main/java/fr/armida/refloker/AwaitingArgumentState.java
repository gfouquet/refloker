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
