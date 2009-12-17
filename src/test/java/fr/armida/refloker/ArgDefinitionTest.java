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

import org.junit.Before;
import org.junit.Test;

import static fr.armida.refloker.Reflector.execute;
import static fr.armida.refloker.Reflector.on;
import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Grégory
 * Date: 12 déc. 2009
 * Time: 09:08:22
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"UnusedDeclaration"})
public class ArgDefinitionTest {
    public static class Dummy {

    }
    public static class TargetClass {
        private Object registeredArgOne;
        private Object registeredArgTwo;
        private void register(Integer i, Double d) {
            registeredArgOne = i;
            registeredArgTwo = d;
        }
    }
    private ArgDefinition<Number, MethodInvocation<Dummy>> objectUnderTest;
    private final MethodInvocation<Dummy> mockMethodInvocation = createMock(MethodInvocation.class);
    private final Number argOne = Integer.valueOf(1);
    private final Number argTwo = Double.valueOf(2.0);

    @Before public void setUpObjectUnderTest() {
        objectUnderTest = ArgDefinition.createDefinitionForArgOfMethod(argOne, mockMethodInvocation);
    }

    @Test
    public void shouldAddArgTwoWithoutTypeDefinitionOfArgOne() {
        // expectation
        ArgDefinition<Number, MethodInvocation<Dummy>> stubArgDef = ArgDefinition.createDefinitionForArgOfMethod(argTwo, mockMethodInvocation);
        expect(mockMethodInvocation.andArg(argTwo)).andReturn(stubArgDef);
        replay(mockMethodInvocation);

        // when
        ArgDefinition<Number, MethodInvocation<Dummy>> secondArgDefinition = objectUnderTest.andArg(argTwo);

        // then
        verify(mockMethodInvocation);
        assertThat(secondArgDefinition, is(equalTo(stubArgDef)));

    }
    @Test public void shouldInvokeRegisterMethodOfTargetClass() {
        TargetClass target = new TargetClass();

        ArgDefinition<Number, MethodInvocation<TargetClass>> register = on(target).invokeHiddenMethod("register").withArg(argOne).andArg(argTwo);
		// moderately satisfying overload of execute required to fix grammar
		execute(register);
		assertThat((Number) target.registeredArgOne, is(argOne));
		assertThat((Number) target.registeredArgTwo, is(argTwo));
    }
}

