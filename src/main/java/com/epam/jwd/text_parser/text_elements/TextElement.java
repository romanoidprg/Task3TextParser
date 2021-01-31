package com.epam.jwd.text_parser.text_elements;

import java.util.List;

public interface TextElement {
    String getText();

    String getClearText(ElementType elementType);

    List<TextElement> getElements(int level);
}