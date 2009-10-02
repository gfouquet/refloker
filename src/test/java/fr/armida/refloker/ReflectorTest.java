package fr.armida.refloker;

import static fr.armida.refloker.Reflector.executeAndReturnValue;
import static fr.armida.refloker.Reflector.on;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import fr.armida.refloker.ReflectionTarget;
import fr.armida.refloker.Reflector;
import fr.armida.refloker.util.NullArgumentException;

public class ReflectorTest {
	private static final String EXPECTED_FIELD_VALUE = "expected";

	private static class TargetClass {
		private String hiddenField;
	}

	private TargetClass target = new TargetClass();

	@Test
	public void shouldCreateANewReflectionTarget() {
		ReflectionTarget<TargetClass> rt1 = Reflector.on(target);
		ReflectionTarget<TargetClass> rt2 = Reflector.on(target);

		assertThat(rt1, is(notNullValue()));
		assertThat(rt2, is(CoreMatchers.<Object> notNullValue()));
		assertThat(rt1, is(not(rt2)));
	}

	@Test(expected = NullArgumentException.class)
	public void shouldNotAcceptNullTarget() {
		Reflector.on(null);
	}

	@Test
	public void shouldReadHiddenFieldOfTarget() {
		target.hiddenField = EXPECTED_FIELD_VALUE;

		Object result = executeAndReturnValue(on(target).getHiddenField(
				"hiddenField"));

		assertThat((String) result, is(EXPECTED_FIELD_VALUE));
	}

	@Test
	public void shouldSetHiddenFieldOfTarget() {
		Reflector.execute(on(target)
				.setHiddenField("hiddenField").to(EXPECTED_FIELD_VALUE));

		assertThat(target.hiddenField, is(EXPECTED_FIELD_VALUE));
	}

}