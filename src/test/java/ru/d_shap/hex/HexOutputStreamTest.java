///////////////////////////////////////////////////////////////////////////////////////////////////
// Hex library converts bytes to hex representation and vice versa.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Hex library.
//
// Hex library is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Hex library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.hex;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link HexOutputStream}.
 *
 * @author Dmitry Shapovalov
 */
public final class HexOutputStreamTest {

    /**
     * Test class constructor.
     */
    public HexOutputStreamTest() {
        super();
    }

    /**
     * {@link HexOutputStream} class test.
     */
    @Test
    public void writeUpperCaseTest() {
        Assert.assertEquals(1, 2);
    }

    /**
     * {@link HexOutputStream} class test.
     */
    @Test
    public void writeLowerCaseTest() {
        Assert.assertEquals(1, 2);
    }

}
