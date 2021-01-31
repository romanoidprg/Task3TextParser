package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.ElementType;
import com.epam.jwd.text_parser.text_elements.impl.SentenceHolder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SentenceParserTest {

    @Test
    public void parse() {
        String text = "Hallo, mister";
        Parser lexemeParser = new LexemeParser();
        Parser sentenceParser = new SentenceParser(lexemeParser);
        ElementHolder sentence = new SentenceHolder("!");
        sentenceParser.parse(sentence, text);
        String expected = "Hallomister";
        assertEquals(expected, sentence.getClearText(ElementType.WORD));
        expected = ",";
        assertEquals(expected, sentence.getClearText(ElementType.WITH_WORD_SYMBOL));
    }
}