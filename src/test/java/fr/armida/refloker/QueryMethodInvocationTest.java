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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import fr.armida.refloker.MethodInvocation;

public class QueryMethodInvocationTest {
	private class Target {
		public String visibleQuery() {
			return "visibleQuery";
		}

		public String visibleQueryWithArg(Integer arg) {
			return "visibleQueryWithArg";
		}

		public String visibleQueryWithArgOfSupertype(Number arg) {
			return "visibleQueryWithArgOfSupertype";
		}

		public String visibleQueryWithTwoArgs(Number arg1,
				Collection<String> arg2) {
			return "visibleQueryWithTwoArgs";
		}
	}

	private final Target target = createMock(Target.class);
	private MethodInvocation<Target> objectUnderTest;

	@Test
	public void shouldInvokeTargetDotVisibleQuery() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleQuery");

		expect(target.visibleQuery()).andReturn("res");
		replay(target);

		String res = (String) objectUnderTest.invokeMethodAndReturnResult();

		assertThat(res, equalTo("res"));
		verify(target);
	}

	@Test
	public void shouldInvokeTargetDotVisibleQueryWithArg() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleQueryWithArg");

		Integer expectedArg = 12;

		expect(target.visibleQueryWithArg(expectedArg)).andReturn("res");
		replay(target);

		objectUnderTest.withArg(expectedArg);
		String res = (String) objectUnderTest.invokeMethodAndReturnResult();

		assertThat(res, equalTo("res"));
		verify(target);
	}

	@Test
	public void shouldInvokeTargetDotVisibleQueryWithArgOfSupertype() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleQueryWithArgOfSupertype");

		Integer expectedArg = 12;

		expect(target.visibleQueryWithArgOfSupertype(expectedArg)).andReturn(
				"res");
		replay(target);

		objectUnderTest.withArg(expectedArg).ofType(Number.class);
		String res = (String) objectUnderTest.invokeMethodAndReturnResult();

		assertThat(res, equalTo("res"));
		verify(target);
	}

	@Test
	public void shouldInvokeTargetDotVisibleQueryWithTwoArgs() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleQueryWithTwoArgs");

		Number expectedArgOne = 12;
		Collection<String> expectedArgTwo = Collections.emptyList();

		expect(target.visibleQueryWithTwoArgs(expectedArgOne, expectedArgTwo))
				.andReturn("res");
		replay(target);

		objectUnderTest.withArg(expectedArgOne).ofType(Number.class).andArg(
				expectedArgTwo).ofType(Collection.class);
		String res = (String) objectUnderTest.invokeMethodAndReturnResult();

		assertThat(res, equalTo("res"));
		verify(target);
	}

	public void shouldInvokeTargetDotVisibleQueryWithNullArg() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleQueryWithArg");

		Integer expectedArg = null;

		expect(target.visibleQueryWithArg(expectedArg)).andReturn("res");
		replay(target);

		objectUnderTest.withArg(expectedArg).ofType(Integer.class);
		String res = (String) objectUnderTest.invokeMethodAndReturnResult();

		assertThat(res, equalTo("res"));
		verify(target);
	}

}
