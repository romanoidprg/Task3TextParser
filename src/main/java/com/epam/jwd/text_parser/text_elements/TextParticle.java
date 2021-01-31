package com.epam.jwd.text_parser.text_elements;

import java.util.List;

public class TextParticle implements TextElement {
    private final String text;
    private ElementType elementType;

    public TextParticle(String text, ElementType elementType) {
        this.elementType = elementType;
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getClearText(ElementType elementType) {
        if ((this.elementType == elementType) | (elementType == ElementType.LEXEME)) {
            return text;
        } else {
            return "";
        }
    }

    @Override
    public List<TextElement> getElements(int level) {
        return null;
    }
}