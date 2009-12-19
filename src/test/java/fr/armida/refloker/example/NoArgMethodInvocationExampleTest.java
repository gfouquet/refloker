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

import static fr.armida.refloker.Reflector.declaredIn;
import static fr.armida.refloker.Reflector.execute;
import static fr.armida.refloker.Reflector.executeAndReturnValue;
import static fr.armida.refloker.Reflector.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;



public class NoArgMethodInvocationExampleTest {
	public static class Example {
		public boolean publicVoidMethodInvoked;
		public boolean privateVoidMethodInvoked;

		public void voidMethod() {
			publicVoidMethodInvoked = true;
		}

		public String stringMethod() {
			return "invoked";
		}

		private void privateVoidMethod() {
			privateVoidMethodInvoked = true;
		}
	}

	/**
	 * this class only inherits of {@link Example} and nothing else.
	 * 
	 * @author Grégory
	 * 
	 */
	public static class ExampleSubclass extends Example {

	}

	private Example example;

	@Before
	public void setUpExample() {
		example = new Example();
	}

	@Test
	public void shouldInvokeVisibleVoidMethod() {
		execute(on(example).invokeMethod("voidMethod"));

		assertThat(example.publicVoidMethodInvoked, is(true));
	}

	@Test
	public void shouldInvokeVisibleStringMethod() {
		String value = (String) executeAndReturnValue(on(example).invokeMethod("stringMethod"));

		assertThat(value, is("invoked"));
	}

	@Test
	public void shouldInvokeHiddenVoidMethod() {
		execute(on(example).invokeHiddenMethod("privateVoidMethod"));

		assertThat(example.privateVoidMethodInvoked, is(true));
	}

	@Test
	public void shouldInvokeMethodDeclaredInSuperclass() {
		ExampleSubclass subExample = new ExampleSubclass();

		execute(on(subExample).invokeMethod("voidMethod", declaredIn(Example.class)));

		assertThat(subExample.publicVoidMethodInvoked, is(true));
	}

	@Test
	public void shouldInvokeHiddenMethodDeclaredInSuperclass() {
		ExampleSubclass subExample = new ExampleSubclass();

		execute(on(subExample).invokeHiddenMethod("privateVoidMethod", declaredIn(Example.class)));

		assertThat(subExample.privateVoidMethodInvoked, is(true));
	}

}
