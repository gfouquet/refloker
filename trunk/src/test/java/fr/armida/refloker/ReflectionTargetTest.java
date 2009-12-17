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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.armida.refloker.MethodInvocation;
import fr.armida.refloker.ReflectionTarget;

public class ReflectionTargetTest {
	private static class TargetClass {
	}

	private ReflectionTarget<TargetClass> objectUnderTest;

	private TargetClass target = new TargetClass();

	@Before
	public void setUp() {
		objectUnderTest = new ReflectionTarget<TargetClass>(target);
	}

	@Test
	public void shouldCreateANewHiddenFieldRead() {
		ExecutableQueryState eqs1 = objectUnderTest.getHiddenField("field");
		ExecutableQueryState eqs2 = objectUnderTest.getHiddenField("field");

		assertNonNullDifferentValues(eqs1, eqs2);
	}

	@Test
	public void shouldCreateANewMethodInvocation() {
		MethodInvocation<TargetClass> mi = objectUnderTest
				.invokeMethod("method");
		MethodInvocation<TargetClass> mimi = objectUnderTest
				.invokeMethod("method");

		assertNonNullDifferentValues(mi, mimi);
	}

	private <V> void assertNonNullDifferentValues(V v, V w) {
		assertThat(v, is(notNullValue()));
		assertThat(w, is(notNullValue()));
		assertThat(v, is(not(w)));
	}

	@Test
	public void shouldCreateANewHiddenMethodInvocation() {
		MethodInvocation<TargetClass> mi = objectUnderTest
				.invokeHiddenMethod("field");
		MethodInvocation<TargetClass> mimi = objectUnderTest
				.invokeHiddenMethod("field");

		assertNonNullDifferentValues(mi, mimi);
	}

	@Test
	public void shouldCreateANewExecutableQueryState() {
		ExecutableQueryState eqs1 = objectUnderTest.getField("field");
		ExecutableQueryState eqs2 = objectUnderTest.getField("field");

		assertNonNullDifferentValues(eqs1, eqs2);
	}

}
