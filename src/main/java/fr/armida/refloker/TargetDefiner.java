package fr.armida.refloker;


import fr.armida.refloker.util.AssertNotNull;

public class TargetDefiner implements TargetUndefinedState {

	public <TARGET> SelectingOperationState<TARGET> on(TARGET target) {
		AssertNotNull.assertArgumentIsNotNull(target, "target");
		return new ReflectionTarget<TARGET>(target);
	}

}
