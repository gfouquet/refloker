package fr.armida.refloker;

public interface TargetUndefinedState {
	<TARGET> SelectingOperationState<TARGET> on(TARGET target);
}
