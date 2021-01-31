package com.epam.jwd.text_parser.process;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ExprCalcTest {

    @Test
    public void infixToRPN_WhenCorrectInfix_ShouldReturnRPN() {
        String[] input = {"(", "~", "23", ">>", "2", "&", "71", ")", "|", "1200"};
        String[] expected = {"23", "~", "2", ">>", "71", "&", "1200", "|"};
        assertArrayEquals(expected, ExprCalc.infixToRPN(input));
    }

    @Test
    public void RPNtoInt_WhenCorrectRPN_ShouldReturnResult() {
        String[] input = {"23", "~", "2", ">>", "71", "&", "1200", "|"};
        int expected = (~23 >> 2 & 71) | 1200;
        assertEquals(expected, ExprCalc.RPNtoInt(input));
    }

    @Test
    public void bitCalc_WhenExprCorrect_ShouldReuturnResultAsString() {
        String expected = "1266";
        assertEquals(expected, ExprCalc.bitCalc("(~23>>2&71)|1200"));
        expected = "%(~23>>2&71)|1200";
        assertEquals(expected, ExprCalc.bitCalc("%(~23>>2&71)|1200"));
    }
}