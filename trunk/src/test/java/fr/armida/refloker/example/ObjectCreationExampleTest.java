package fr.armida.refloker.example;

import static fr.armida.refloker.Reflector.createA;
import static fr.armida.refloker.Reflector.executeAndReturnValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ObjectCreationExampleTest {
	public static class Example {
		public boolean argOne;
		public Number argTwo;

		public Example() {
		}

		private Example(boolean argOne) {
			this.argOne = argOne;
		}

		public Example(boolean argOne, Number argTwo) {
			this.argOne = argOne;
			this.argTwo = argTwo;
		}
	}

	@Test
	public void shouldCreateAnExampleWithoutArguments() {
		Example ex = executeAndReturnValue(createA(Example.class));

		assertThat(ex, is(notNullValue()));
	}
}
