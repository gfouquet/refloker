package fr.armida.refloker;

public interface AwaitingValueState {
	ExecutableCommandState to(Object newValue);
}
