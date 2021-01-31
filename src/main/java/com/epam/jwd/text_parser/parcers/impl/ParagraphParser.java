package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.TextRegexes;
import com.epam.jwd.text_parser.text_elements.impl.SentenceHolder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements Parser {
    Parser nextParser;

    public ParagraphParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(ElementHolder paragraph, String text) {

        String sentenceText;
        String charOfSentenceEnd;
        ElementHolder sentence;

        Pattern pattern = Pattern.compile(TextRegexes.SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            StringBuilder s = new StringBuilder(matcher.group());
            if ((s.length() > 3) && (s.substring(s.length() - 3).contains("..."))) {
                charOfSentenceEnd = "...";
                sentenceText = s.delete(s.length() - 3, s.length() - 1).toString();
            } else {
                charOfSentenceEnd = s.substring(s.length() - 1);
                sentenceText = s.deleteCharAt(s.length() - 1).toString();
            }
            sentence = new SentenceHolder(charOfSentenceEnd);
            paragraph.addElement(sentence);
            nextParser.parse(sentence, sentenceText);
        }
    }
}
