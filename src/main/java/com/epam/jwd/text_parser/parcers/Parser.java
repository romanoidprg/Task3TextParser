package com.epam.jwd.text_parser.parcers;

import com.epam.jwd.text_parser.text_elements.ElementHolder;

public interface Parser {
    void parse(ElementHolder elementHolder, String text);
}
