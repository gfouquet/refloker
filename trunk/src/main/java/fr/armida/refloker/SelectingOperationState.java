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

	// AwaitingValueState setField(String fieldame);
}
