package com.epam.jwd.text_parser.parcers.impl;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.process.ExprCalc;
import com.epam.jwd.text_parser.text_elements.ElementHolder;
import com.epam.jwd.text_parser.text_elements.ElementType;
import com.epam.jwd.text_parser.text_elements.TextParticle;
import com.epam.jwd.text_parser.text_elements.TextRegexes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements Parser {

    @Override
    public void parse(ElementHolder lexemeHolder, String text) {
        TextParticle textParticle;
        Pattern pattern = Pattern.compile(TextRegexes.NOT_WITH_WORD_SYMBOL_REGEX);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            textParticle = new TextParticle(matcher.group(), ElementType.NOT_WITH_WORD_SYMBOL);
            lexemeHolder.addElement(textParticle);
        } else {
            pattern = Pattern.compile(TextRegexes.EXPRESSION_REGEX);
            matcher = pattern.matcher(text);
            if (matcher.matches()) {
                textParticle = new TextParticle(ExprCalc.bitCalc(matcher.group()), ElementType.EXPRESSION);
                lexemeHolder.addElement(textParticle);
            } else {
                wordAndSymbParse(lexemeHolder, text);
            }
        }
    }

    private void wordAndSymbParse(ElementHolder lexemeHolder, String text) {
        Pattern pattern = Pattern.compile(TextRegexes.LEXEME_BEGIN_FROM_SYMBOL_REGEX);
        Matcher matcher = pattern.matcher(text);
        boolean isSymbPattern;
        if (matcher.find()) {
            isSymbPattern = false;
        } else {
            isSymbPattern = true;
        }
        matcher.reset();
        pattern = Pattern.compile(TextRegexes.WORD_REGEX);
        Pattern patternSymb = Pattern.compile(TextRegexes.WITH_WORD_SYMBOL_REGEX);
        boolean repeatS = false;
        boolean repeatW = false;
        do {
            if (isSymbPattern) {
                matcher.usePattern(patternSymb);
                if (matcher.find()) {
                    lexemeHolder.addElement(new TextParticle(matcher.group(), ElementType.WITH_WORD_SYMBOL));
                    repeatS = true;
                } else {
                    repeatS = false;
                }
                isSymbPattern = false;
            } else {
                matcher.usePattern(pattern);
                if (matcher.find()) {
                    lexemeHolder.addElement(new TextParticle(matcher.group(), ElementType.WORD));
                    repeatW = true;
                } else {
                    repeatW = false;
                }
                isSymbPattern = true;
            }
        } while (repeatS | repeatW);
    }
}
