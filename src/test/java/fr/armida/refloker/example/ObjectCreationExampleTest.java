/**
 * This file is part of the Refloker library, a small DSL for reflective invocation
 * Copyright (C) 2009  Gr�gory Fouquet
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package fr.armida.refloker.example;

import static fr.armida.refloker.Reflector.createA;
import static fr.armida.refloker.Reflector.executeAndReturnValue;
import static fr.armida.refloker.Reflector.ofType;
import static fr.armida.refloker.Reflector.usingHiddenConstructor;
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

		public Example(Boolean argOne) {
			this.argOne = argOne;
		}

		private Example(Integer argTwo) {
			this.argTwo = argTwo;
		}

		public Example(Boolean argOne, Number argTwo) {
			this.argOne = argOne;
			this.argTwo = argTwo;
		}
	}

	@Test
	public void shouldCreateAnExampleWithoutArguments() {
		Example ex = executeAndReturnValue(createA(Example.class));

		assertThat(ex, is(notNullValue()));
	}

	@Test
	public void shouldCreateAnExampleWithBooleanArgument() {
		Example ex = executeAndReturnValue(createA(Example.class).withArg(true));

		assertThat(ex.argOne, is(true));
	}

	@Test
	public void shouldCreateAnExampleFromPrivateConstructor() {
		Example ex = executeAndReturnValue(createA(Example.class, usingHiddenConstructor()).withArg(1));

		assertThat(ex.argTwo, is((Number) 1));
	}

	@Test
	public void shouldCreateAnExampleWithBooleanAndNumberArguments() {
		Example ex = executeAndReturnValue(createA(Example.class).withArg(true).andArg(1, ofType(Number.class)));

		assertThat(ex.argOne, is(true));
		assertThat(ex.argTwo, is((Number) 1));
	}
}
