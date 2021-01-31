package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.ElementType;
import com.epam.jwd.text_parser.text_elements.impl.ParagraphHolder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParagraphParserTest {

    @Test
    public void parse() {
        String text = "    Hallo, mister! I'm a dog (big dog)...";
        ElementHolder paragraph = new ParagraphHolder();
        Parser lexemeParser = new LexemeParser();
        Parser sentenceParser = new SentenceParser(lexemeParser);
        Parser paragraphParser = new ParagraphParser(sentenceParser);
        paragraphParser.parse(paragraph, text);
        String expected = "HallomisterImadogbigdog";
        assertEquals(expected, paragraph.getClearText(ElementType.WORD));
        expected = ",'()";
        assertEquals(expected, paragraph.getClearText(ElementType.WITH_WORD_SYMBOL));
    }
}