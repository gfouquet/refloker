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

import static fr.armida.refloker.Reflector.ofType;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;

import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import fr.armida.refloker.MethodInvocation;

public class CommandMethodInvocationTest {
	private class Target {
		public void visibleCommand() {

		}

		public void visibleCommandWithArg(Integer arg) {

		}

		public void visibleCommandWithArgOfSupertype(Number arg) {

		}

		public void visibleCommandWithTwoArgs(Number arg1,
				Collection<String> arg2) {

		}
	}

	private final Target target = createMock(Target.class);
	private MethodInvocation<Target> objectUnderTest;

	@Test
	public void shouldInvokeTargetDotVisibleCommand() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleCommand");

		target.visibleCommand();
		replay(target);

		objectUnderTest.invokeMethod();

		verify(target);
	}

	@Test
	public void shouldInvokeTargetDotVisibleCommandWithArg() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleCommandWithArg");

		Integer expectedArg = 12;

		target.visibleCommandWithArg(expectedArg);
		replay(target);

		objectUnderTest.withArg(expectedArg);
		objectUnderTest.invokeMethod();

		verify(target);
	}

	@Test
	public void shouldInvokeTargetDotVisibleCommandWithArgOfSupertype() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleCommandWithArgOfSupertype");

		Integer expectedArg = 12;

		target.visibleCommandWithArgOfSupertype(expectedArg);
		replay(target);

		objectUnderTest.withArg(expectedArg, ofType(Number.class));
		objectUnderTest.invokeMethod();

		verify(target);
	}

	@Test
	public void shouldInvokeTargetDotVisibleCommandWithTwoArgs() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleCommandWithTwoArgs");

		Number expectedArgOne = 12;
		Collection<String> expectedArgTwo = Collections.emptyList();

		target.visibleCommandWithTwoArgs(expectedArgOne, expectedArgTwo);
		replay(target);

		objectUnderTest.withArg(expectedArgOne, ofType(Number.class)).andArg(expectedArgTwo, ofType(Collection.class));
		objectUnderTest.invokeMethod();

		verify(target);
	}

	public void shouldInvokeTargetDotVisibleCommandWithNullArg() {
		objectUnderTest = new MethodInvocation<Target>(target,
				"visibleCommandWithArg");

		Integer expectedArg = null;

		target.visibleCommandWithArg(expectedArg);
		replay(target);

		objectUnderTest.withArg(expectedArg, ofType(Integer.class));
		objectUnderTest.invokeMethod();

		verify(target);
	}

}
