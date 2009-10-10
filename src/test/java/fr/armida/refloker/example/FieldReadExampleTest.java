package fr.armida.refloker.example;

import static fr.armida.refloker.Reflector.executeAndReturnValue;
import static fr.armida.refloker.Reflector.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.armida.refloker.FieldRead;
import fr.armida.refloker.Reflector;

public class FieldReadExampleTest {
	public static class Example {
		private String hiddenField = "hidden field";
		public String visibleField = "visible field";
	}

	/**
	 * this class only inherits the {@link Example} class
	 * 
	 * @author Grégory
	 * 
	 */
	public static class ExampleSubclass extends Example {
	}

	private Example example;
	private ExampleSubclass subExample;

	@Before
	public void setUpExample() {
		example = new Example();
	}

	@Before
	public void setUpSubExample() {
		subExample = new ExampleSubclass();
	}

	@Test
	public void shouldReadHiddenField() {
		FieldRead<Example> fieldReadOperation = Reflector.on(example).getHiddenField("hiddenField");
		Object value = Reflector.executeAndReturnValue(fieldReadOperation);

		assertThat(value, is((Object) "hidden field"));
	}

	@Test
	public void shouldSetVisibleField() {
		Object value = executeAndReturnValue(on(example).getField("visibleField"));

		assertThat(value, is((Object) "visible field"));
	}

	@Test
	public void shouldReadVisibleFieldDefinedInSuperclass() {
		// when the field is defined in a superclass, we have to indicate it
		Object value = executeAndReturnValue(on(subExample).getField("visibleField").definedIn(Example.class));

		assertThat(value, is((Object) "visible field"));
	}
}
