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
package fr.armida.refloker.util;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.armida.refloker.util.CollectionAdapter;

public class CollectionAdapterTest {
	private static class Foo {
		private String bar;

		public Foo(String property) {
			super();
			this.bar = property;
		}
	}

	private final List<Foo> foos = new ArrayList<Foo>();
	{
		foos.add(new Foo("un"));
		foos.add(new Foo("deux"));
	}

	@Test
	public void shouldIterateOverBarsOfFoos() {
		CollectionAdapter<Foo, String> objectUnderTest = new CollectionAdapter<Foo, String>(
				foos) {
			@Override
			protected String adapt(Foo sourceItem) {
				return sourceItem.bar;
			}
		};

		assertThat(objectUnderTest, hasItems("un", "deux"));
	}
}
