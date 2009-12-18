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
	 * Selects an opeteration of field set. The field is declared in the
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
}
