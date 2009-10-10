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
