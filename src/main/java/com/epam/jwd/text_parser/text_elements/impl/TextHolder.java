package com.epam.jwd.text_parser.text_elements.impl;

import com.epam.jwd.text_parser.text_elements.ElementHolder;

public class TextHolder extends ElementHolder {
    @Override
    public String getText() {
        StringBuilder text = new StringBuilder(super.getText());
        return text.deleteCharAt(text.length() - 1).toString();
    }
}
