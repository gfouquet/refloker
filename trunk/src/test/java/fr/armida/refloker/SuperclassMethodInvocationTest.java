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
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SuperclassMethodInvocationTest {
	public static class SuperclassOfTarget {
		public boolean invoked;
		public void method() {
			invoked = true;
		}
	}
	
	public static class Target extends SuperclassOfTarget {
		
	}
	
	private Target target;
	private MethodInvocation<Target> testedObject;
	
	@Before
	public void setUpTarget() {
		target = new Target();
	}

	@Test
	public void shouldInvokeMethodOfSuperclass() {
		// given
		testedObject = new MethodInvocation<Target>(target, "method");
		testedObject.declaredIn(SuperclassOfTarget.class);
		
		// when
		testedObject.executeAndReturnValue();
		
		// then 
		assertThat(target.invoked, is(true));

	}
}
