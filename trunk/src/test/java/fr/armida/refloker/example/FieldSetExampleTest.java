package fr.armida.refloker.example;

import static fr.armida.refloker.Reflector.execute;
import static fr.armida.refloker.Reflector.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.armida.refloker.FieldSet;
import fr.armida.refloker.Reflector;

public class FieldSetExampleTest {
	public static class Example {
		private String hiddenField;
		public String visibleField;

		public String getHiddenField() {
			return hiddenField;
		}

		public String getVisibleString() {
			return visibleField;
		}
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
	public void shouldSetHiddenField() {
		FieldSet<Example> fieldSetOperation = Reflector.on(example).setHiddenField("hiddenField").to(
				"hidden field was set");
		Reflector.execute(fieldSetOperation);

		assertThat(example.getHiddenField(), is("hidden field was set"));
	}

	@Test
	public void shouldSetVisibleField() {
		execute(on(example).setField("visibleField").to("visible field was set"));

		assertThat(example.getVisibleString(), is("visible field was set"));
	}

	@Test
	public void shouldSetHiddenFieldDefinedInSuperclass() {
		// when the field is defined in a superclass, we have to indicate it
		execute(on(subExample).setHiddenField("hiddenField").definedIn(Example.class).to(
				"hidden field was set in superclass"));

		assertThat(subExample.getHiddenField(), is("hidden field was set in superclass"));
	}
}
