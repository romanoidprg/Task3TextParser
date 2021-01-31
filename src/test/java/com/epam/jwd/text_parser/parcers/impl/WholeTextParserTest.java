package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.ElementType;
import com.epam.jwd.text_parser.text_elements.impl.TextHolder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WholeTextParserTest {

    @Test
    public void parse() {
        String text = "    Hallo, mister! I'm a dog (big dog)...";
        ElementHolder wholeText = new TextHolder();
        Parser lexemeParser = new LexemeParser();
        Parser sentenceParser = new SentenceParser(lexemeParser);
        Parser paragraphParser = new ParagraphParser(sentenceParser);
        Parser textParser = new WholeTextParser(paragraphParser);
        textParser.parse(wholeText, text);
        String expected = "HallomisterImadogbigdog";
        assertEquals(expected, wholeText.getClearText(ElementType.WORD));
        expected = ",'()";
        assertEquals(expected, wholeText.getClearText(ElementType.WITH_WORD_SYMBOL));
    }
}