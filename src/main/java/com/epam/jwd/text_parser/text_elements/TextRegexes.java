package com.epam.jwd.text_parser.text_elements;

public abstract class TextRegexes {
    public static final String PARAGRAPH_BEGIN_REGEX = "(    |\\t)";
    public static final String SENTENCE_REGEX = "[A-Z]{1}.*?[.!?]{1}";
    public static final String SENTENCE_DELIM_REGEX = "\\s+";
    public static final String NOT_WITH_WORD_SYMBOL_REGEX = "[^\\p{Alnum}]";
    public static final String EXPRESSION_REGEX = "[\\p{Digit}<>()&|^~]+";
    public static final String LEXEME_BEGIN_FROM_SYMBOL_REGEX = "^[\\p{Alnum}-]";
    public static final String WORD_REGEX = "[\\p{Alnum}-]+";
    public static final String WITH_WORD_SYMBOL_REGEX = "[^\\p{Alnum}&&[^-]]";
}
