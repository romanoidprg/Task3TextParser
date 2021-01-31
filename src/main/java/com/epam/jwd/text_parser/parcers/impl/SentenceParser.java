package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.TextRegexes;
import com.epam.jwd.text_parser.text_elements.impl.LexemeHolder;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SentenceParser implements Parser {
    Parser nextParser;

    public SentenceParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(ElementHolder sentence, String text) {
        Pattern pattern = Pattern.compile(TextRegexes.SENTENCE_DELIM_REGEX);
        List<String> lexemes = Arrays.stream(pattern.split(text)).filter(l
                -> !l.equals("")).collect(Collectors.toList());
        ElementHolder lexemeHolder;
        for (String l : lexemes) {
            lexemeHolder = new LexemeHolder();
            sentence.addElement(lexemeHolder);
            nextParser.parse(lexemeHolder, l);
        }
    }
}
