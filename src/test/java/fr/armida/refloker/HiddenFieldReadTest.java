/**
 *  This file is part of the Refloker library, a small DSL for reflective invocation
 *  Copyright (C) 2009  Gr�gory Fouquet
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
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.armida.refloker.HiddenFieldRead;



public class HiddenFieldReadTest {
	private static class TargetSuperclass {
		private String superfieldToRead;
	}

	private static class TargetClass extends TargetSuperclass {
		private String fieldToRead;
	}

	private static final String EXPECTED_FIELD_VALUE = "expected value";

	private HiddenFieldRead<TargetClass> objectUnderTest;
	private TargetClass target;

	@Test
	public void shouldReadFieldOfTarget() {
		target = new TargetClass();
		target.fieldToRead = EXPECTED_FIELD_VALUE;
		objectUnderTest = new HiddenFieldRead<TargetClass>(target, "fieldToRead");

		Object result = objectUnderTest.readField();

		assertThat(result, equalTo((Object) EXPECTED_FIELD_VALUE));
	}

	@Test
	public void shouldReadFieldOfSuperclass() {
		target = new TargetClass();
		((TargetSuperclass)target).superfieldToRead = EXPECTED_FIELD_VALUE;
		objectUnderTest = new HiddenFieldRead<TargetClass>(target, "superfieldToRead");

		objectUnderTest.declaredIn(TargetSuperclass.class);
		Object result = objectUnderTest.readField();

		assertThat(result, equalTo((Object) EXPECTED_FIELD_VALUE));
	}

}
