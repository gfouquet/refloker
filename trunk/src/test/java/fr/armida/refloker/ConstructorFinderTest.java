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
