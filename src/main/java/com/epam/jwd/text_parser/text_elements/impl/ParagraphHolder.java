package com.epam.jwd.text_parser.text_elements.impl;

import com.epam.jwd.text_parser.text_elements.ElementHolder;

public class ParagraphHolder extends ElementHolder {
    @Override
    public String getText() {
        StringBuilder text = new StringBuilder(super.getText());
        text.insert(0, "    ");
        text.deleteCharAt(text.length() - 1);
        text.append("\n");
        return text.toString();
    }
}
