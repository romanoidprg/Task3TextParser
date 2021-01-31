package com.epam.jwd.text_parser.text_elements.impl;

import com.epam.jwd.text_parser.text_elements.ElementHolder;

public class SentenceHolder extends ElementHolder {
    private final String charOfSentenceEnd;

    public SentenceHolder(String charOfSentenceEnd) {
        this.charOfSentenceEnd = charOfSentenceEnd;
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder(super.getText());
        text.deleteCharAt(text.length() - 1);
        text.append(charOfSentenceEnd);
        text.append(" ");
        return text.toString();
    }
}
