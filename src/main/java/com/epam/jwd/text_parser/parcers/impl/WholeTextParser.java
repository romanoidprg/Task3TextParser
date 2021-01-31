package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.TextRegexes;
import com.epam.jwd.text_parser.text_elements.impl.ParagraphHolder;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WholeTextParser implements Parser {
    Parser nextParser;

    public WholeTextParser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(ElementHolder wholeText, String text) {
        String paragraphText;
        ElementHolder paragraph;
        Pattern pattern = Pattern.compile(TextRegexes.PARAGRAPH_BEGIN_REGEX);
        List<String> paragraphs = Arrays.stream(pattern.split(text)).filter(p
                -> !p.equals("")).collect(Collectors.toList());
        for (String p : paragraphs) {
            paragraphText = p.replace("\n", "");
            paragraph = new ParagraphHolder();
            wholeText.addElement(paragraph);
            nextParser.parse(paragraph, paragraphText);
        }
    }
}
