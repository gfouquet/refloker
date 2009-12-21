package fr.armida.refloker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ObjectCreationTest {
	public static class ToCreate {
		public ToCreate() {

		}
		public ToCreate(boolean b) {

		}

		private ToCreate(int i) {

		}
	}

	private ObjectCreation<ToCreate> testedObject;

	@Before
	public void setUpTestedObject() {
		testedObject = new ObjectCreation<ToCreate>(ToCreate.class);
	}

	@Test
	public void shouldCreateObjectWithoutArg() {
		ToCreate created = testedObject.executeAndReturnValue();
		ToCreate created2 = testedObject.executeAndReturnValue();

		assertThat(created, is(not(created2)));
	}
}
