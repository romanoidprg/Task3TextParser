package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.ElementType;
import com.epam.jwd.text_parser.text_elements.impl.LexemeHolder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LexemeParserTest {

    @Test
    public void parse() {
        String text = "(word,)";
        ElementHolder lexemeHolder = new LexemeHolder();
        Parser lexemeParser = new LexemeParser();
        lexemeParser.parse(lexemeHolder, text);
        String expected = "word";
        assertEquals(expected, lexemeHolder.getClearText(ElementType.WORD));
        expected = "(,)";
        assertEquals(expected, lexemeHolder.getClearText(ElementType.WITH_WORD_SYMBOL));
    }
}