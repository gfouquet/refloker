/**
 * This file is part of the Refloker library, a small DSL for reflective invocation
 * Copyright (C) 2009  Grégory Fouquet
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
