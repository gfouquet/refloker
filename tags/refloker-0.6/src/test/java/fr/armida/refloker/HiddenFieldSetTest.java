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
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.armida.refloker.HiddenFieldSet;


public class HiddenFieldSetTest {
	private static class TargetSuperclass {
		private String superfieldToSet;
	}

	private static class TargetClass extends TargetSuperclass {
		private String fieldToSet;
	}

	private static final String EXPECTED_FIELD_VALUE = "expected value";

	private HiddenFieldSet<TargetClass> objectUnderTest;
	private TargetClass target;

	@Test
	public void shouldSetFieldOfTarget() {
		target = new TargetClass();
		objectUnderTest = new HiddenFieldSet<TargetClass>(target, "fieldToSet");

		objectUnderTest.to(EXPECTED_FIELD_VALUE);
		objectUnderTest.setField();

		assertThat(target.fieldToSet, equalTo((Object) EXPECTED_FIELD_VALUE));
	}

	@Test
	public void shouldSetFieldOfSuperclass() {
		target = new TargetClass();
		((TargetSuperclass) target).superfieldToSet = EXPECTED_FIELD_VALUE;
		objectUnderTest = new HiddenFieldSet<TargetClass>(target,
				"superfieldToSet");

		objectUnderTest.definedIn(TargetSuperclass.class).to(
				EXPECTED_FIELD_VALUE);
		objectUnderTest.setField();

		assertThat(((TargetSuperclass) target).superfieldToSet,
				equalTo(EXPECTED_FIELD_VALUE));
	}

}
