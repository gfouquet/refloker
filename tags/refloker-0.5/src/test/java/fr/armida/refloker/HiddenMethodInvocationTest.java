/**
 *  This file is part of the Refloker library, a small DSL for reflective invocation
 *  Copyright (C) 2009  Grégory Fouquet
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.armida.refloker;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.armida.refloker.HiddenMethodInvocation;

public class HiddenMethodInvocationTest {
	private interface Target {
		
	}

	private HiddenMethodInvocation<Target> objectUnderTest;

	@Test
	public void shouldInvokeHiddenMethod() {
		final boolean[] methodInvoked = { false };
		
		Target targetOfReflection = new Target() {
			@SuppressWarnings("unused")
			private void methodToInvoke() {
				methodInvoked[0] = true;
			}
		};

		objectUnderTest = new HiddenMethodInvocation<Target>(
				targetOfReflection, "methodToInvoke");
		objectUnderTest.invokeMethod();
		
		assertThat(methodInvoked[0], is(true));
	}

	@Test
	public void shouldGetHiddenMethodResult() {

		Target targetOfReflection = new Target() {
			@SuppressWarnings("unused")
			private int returnTheValue(int i) {
				return i;
			}
		};

		int expectedResult = 10;
		objectUnderTest = new HiddenMethodInvocation<Target>(
				targetOfReflection, "returnTheValue");
		objectUnderTest.withArg(10).ofType(int.class);
		Object res = objectUnderTest.invokeMethodAndReturnResult();

		assertThat(res, equalTo((Object) expectedResult));
	}
}
