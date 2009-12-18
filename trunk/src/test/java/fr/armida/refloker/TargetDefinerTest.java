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
import static org.hamcrest.CoreMatchers.not;

import org.junit.Test;

import fr.armida.refloker.SelectingOperationState;
import fr.armida.refloker.util.NullArgumentException;

public class TargetDefinerTest {
	private final TargetDefiner testedObject = new TargetDefiner();

	@Test
	public void shouldReturnANewSelectiongOperationState() {
		SelectingOperationState<String> sos = testedObject.on("target");
		SelectingOperationState<String> sos2 = testedObject.on("target");
		assertThat(sos, is(not(sos2)));
	}

	@Test(expected = NullArgumentException.class)
	public void shouldNotAllowNullTargets() {
		testedObject.on(null);
	}
}
