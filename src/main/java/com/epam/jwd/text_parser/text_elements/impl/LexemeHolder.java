package com.epam.jwd.text_parser.text_elements.impl;

import com.epam.jwd.text_parser.text_elements.ElementHolder;

public class LexemeHolder extends ElementHolder {

    @Override
    public String getText() {
        return super.getText() + " ";
    }
}
