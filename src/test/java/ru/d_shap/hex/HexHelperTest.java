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

import java.io.IOException;

/**
 * Tests for {@link HexHelper}.
 *
 * @author Dmitry Shapovalov
 */
public final class HexHelperTest {

    /**
     * Test class constructor.
     */
    public HexHelperTest() {
        super();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexTest() throws IOException {
        Assert.assertEquals("01058CFAF2", HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}));
    }

}
