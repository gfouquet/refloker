package fr.armida.refloker;

class ExecutableCommandFieldSetAdapter<TARGET> implements ExecutableCommandState {

	private final FieldSet<TARGET> fieldSet;

	protected ExecutableCommandFieldSetAdapter(FieldSet<TARGET> fieldSet) {
		super();
		this.fieldSet = fieldSet;
	}

	public void execute() {
		fieldSet.setField();
	}

}
