package fr.armida.refloker;


public class ReflectionTarget<TARGET> {
	private final TARGET target;

	protected ReflectionTarget(TARGET target) {
		this.target = target;
	}

	/**
	 * Defines the read of a public field.
	 * 
	 * @param fieldName
	 * @return
	 */
	public FieldRead<TARGET> getField(String fieldName) {
		return new FieldRead<TARGET>(target, fieldName);
	}

	/**
	 * Defines the read of a restricted access (protected or less) field.
	 * 
	 * @param fieldName
	 * @return
	 */
	public FieldRead<TARGET> getHiddenField(String fieldName) {
		return new HiddenFieldRead<TARGET>(target, fieldName);
	}

	public FieldSet<TARGET> setField(String fieldName) {
		return new FieldSet<TARGET>(target, fieldName);
	}

	public FieldSet<TARGET> setHiddenField(String fieldName) {
		return new HiddenFieldSet<TARGET>(target, fieldName);
	}

	public MethodInvocation<TARGET> invokeMethod(String methodName) {
		return new MethodInvocation<TARGET>(target, methodName);
	}

	public MethodInvocation<TARGET> invokeHiddenMethod(String methodName) {
		return new HiddenMethodInvocation<TARGET>(target, methodName);
	}
}
