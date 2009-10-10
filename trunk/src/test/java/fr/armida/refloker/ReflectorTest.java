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

import static fr.armida.refloker.Reflector.executeAndReturnValue;
import static fr.armida.refloker.Reflector.on;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import fr.armida.refloker.ReflectionTarget;
import fr.armida.refloker.Reflector;
import fr.armida.refloker.util.NullArgumentException;

public class ReflectorTest {
	private static final String EXPECTED_FIELD_VALUE = "expected";

	private static class TargetClass {
		private String hiddenField;
	}

	private TargetClass target = new TargetClass();

	@Test
	public void shouldCreateANewReflectionTarget() {
		ReflectionTarget<TargetClass> rt1 = Reflector.on(target);
		ReflectionTarget<TargetClass> rt2 = Reflector.on(target);

		assertThat(rt1, is(notNullValue()));
		assertThat(rt2, is(CoreMatchers.<Object> notNullValue()));
		assertThat(rt1, is(not(rt2)));
	}

	@Test(expected = NullArgumentException.class)
	public void shouldNotAcceptNullTarget() {
		Reflector.on(null);
	}

	@Test
	public void shouldReadHiddenFieldOfTarget() {
		target.hiddenField = EXPECTED_FIELD_VALUE;

		Object result = executeAndReturnValue(on(target).getHiddenField(
				"hiddenField"));

		assertThat((String) result, is(EXPECTED_FIELD_VALUE));
	}

	@Test
	public void shouldSetHiddenFieldOfTarget() {
		Reflector.execute(on(target)
				.setHiddenField("hiddenField").to(EXPECTED_FIELD_VALUE));

		assertThat(target.hiddenField, is(EXPECTED_FIELD_VALUE));
	}

}