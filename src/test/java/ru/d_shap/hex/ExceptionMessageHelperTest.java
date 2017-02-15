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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link ExceptionMessageHelper}.
 *
 * @author Dmitry Shapovalov
 */
public final class ExceptionMessageHelperTest {

    /**
     * Test class constructor.
     */
    public ExceptionMessageHelperTest() {
        super();
    }

    /**
     * {@link ExceptionMessageHelper} class test.
     */
    @Test
    public void constructorTest() {
        Assertions.assertThat(ExceptionMessageHelper.class).hasOnePrivateConstructor();
    }

    /**
     * {@link ExceptionMessageHelper} class test.
     */
    @Test
    public void createMessageTest() {
        Assertions.assertThat(ExceptionMessageHelper.createWrongHexStringSizeMessage(11)).isEqualTo("Wrong number of symbols in hex string (11)");
        Assertions.assertThat(ExceptionMessageHelper.createWrongHexStringSizeMessage(13)).isEqualTo("Wrong number of symbols in hex string (13)");

        Assertions.assertThat(ExceptionMessageHelper.createWrongResultArrayMessage(16, 13)).isEqualTo("Result array is too small for hex string (13), expected size is (16)");
        Assertions.assertThat(ExceptionMessageHelper.createWrongResultArrayMessage(20, 9)).isEqualTo("Result array is too small for hex string (9), expected size is (20)");

        Assertions.assertThat(ExceptionMessageHelper.createWrongHexSymbol('-')).isEqualTo("Wrong symbol obtained: '-' (45)");
        Assertions.assertThat(ExceptionMessageHelper.createWrongHexSymbol('!')).isEqualTo("Wrong symbol obtained: '!' (33)");
        Assertions.assertThat(ExceptionMessageHelper.createWrongHexSymbol('#')).isEqualTo("Wrong symbol obtained: '#' (35)");

        Assertions.assertThat(ExceptionMessageHelper.createEndOfStreamMessage()).isEqualTo("Unexpected end of stream");
    }

}
