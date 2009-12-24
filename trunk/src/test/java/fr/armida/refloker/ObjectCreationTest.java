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

import static fr.armida.refloker.Reflector.ofType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ObjectCreationTest {
	public static class ToCreate {
		public Object arg;

		public ToCreate() {

		}

		public ToCreate(Boolean b) {
			arg = b;
		}

		public ToCreate(Number n) {
			arg = n;
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

	@Test
	public void shouldCreateObjectWithBoolean() {
		testedObject.withArg(true);
		
		ToCreate created = testedObject.executeAndReturnValue();

		assertThat(created, is(notNullValue()));
		assertThat(created.arg, is((Object) true));
	}

	@Test
	public void shouldCreateObjectWithNumber() {
		testedObject.withArg(1, ofType(Number.class));

		ToCreate created = testedObject.executeAndReturnValue();

		assertThat(created, is(notNullValue()));
		assertThat(created.arg, is((Object) 1));
	}

}
