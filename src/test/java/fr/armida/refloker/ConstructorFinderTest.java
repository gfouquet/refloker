package fr.armida.refloker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;

import org.junit.Test;



public class ConstructorFinderTest {
	private static class ToCreate {
		public ToCreate(boolean b) {

		}

		private ToCreate(int i) {

		}

	}

	private final ConstructorFinder<ToCreate> testedObject = new ConstructorFinder<ToCreate>(ToCreate.class);

	@Test
	public void shouldFindPublicConstructor() throws NoSuchMethodException {
		Constructor<ToCreate> ctor = testedObject.getConstructorFromPublicApi(boolean.class);

		assertThat(ctor, is(notNullValue()));
	}
}
