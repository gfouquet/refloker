package fr.armida.refloker;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Test;

import fr.armida.refloker.SelectingOperationState;
import fr.armida.refloker.util.NullArgumentException;

public class TargetDefinerTest {
	private final TargetDefiner testedObject = new TargetDefiner();

	@Test
	public void shouldReturnANewSelectiongOperationState() {
		SelectingOperationState<String> sos = testedObject.on("target");
		SelectingOperationState<String> sos2 = testedObject.on("target");
		assertThat(sos, is(not(sos2)));
	}

	@Test(expected = NullArgumentException.class)
	public void shouldNotAllowNullTargets() {
		testedObject.on(null);
	}
}
