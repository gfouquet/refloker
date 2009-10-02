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

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class CollectionAdapter<SOURCE_ITEM, ADAPTED_ITEM> extends
		AbstractCollection<ADAPTED_ITEM> {
	private class IteratorAdapter implements Iterator<ADAPTED_ITEM> {
		private final Iterator<SOURCE_ITEM> sourceIterator;

		private IteratorAdapter() {
			sourceIterator = collectionToAdapt.iterator();
		}

		@Override
		public boolean hasNext() {
			return sourceIterator.hasNext();
		}

		@Override
		public ADAPTED_ITEM next() {
			SOURCE_ITEM sourceItem = sourceIterator.next();
			return adapt(sourceItem);
		}

		@Override
		public void remove() {
			sourceIterator.remove();

		}
	}

	private final Collection<SOURCE_ITEM> collectionToAdapt;

	public CollectionAdapter(Collection<SOURCE_ITEM> collectionToAdapt) {
		super();
		this.collectionToAdapt = collectionToAdapt;
	}

	@Override
	public int size() {
		return collectionToAdapt.size();
	}

	@Override
	public Iterator<ADAPTED_ITEM> iterator() {
		return new IteratorAdapter();
	}

	/**
	 * should return the adapted item for the source item received as an
	 * argument.
	 * 
	 * @param sourceItem
	 * @return
	 */
	protected abstract ADAPTED_ITEM adapt(SOURCE_ITEM sourceItem);
}
