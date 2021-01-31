package com.epam.jwd.text_parser.process;

import com.epam.jwd.text_parser.text_elements.ElementType;
import com.epam.jwd.text_parser.text_elements.TextElement;
import com.epam.jwd.text_parser.text_elements.impl.TextHolder;

import java.util.Comparator;

public class TextSorter {

    public static void sortParagraphsBySentenceQuantity(TextHolder textHolder) {
        textHolder.getElements(0).sort(Comparator.comparing(p -> p.getElements(0).size()));
    }

    public static void sortSentencesByElementLength(TextHolder textHolder, ElementType elementType) {
        for (TextElement el : textHolder.getElements(0)) {
            el.getElements(0).sort(Comparator.comparing(s -> s.getClearText(elementType).length()));
        }
    }

    public static void sortLexemesBySymbol(TextHolder textHolder, char symbol) {
        for (TextElement el : textHolder.getElements(1)) {
            Comparator<TextElement> comparator = Comparator.comparing(lexeme
                    -> lexeme.getClearText(ElementType.LEXEME).chars().mapToObj(c
                    -> (char) c).filter(c -> c == symbol).count());
            el.getElements(0).sort(comparator.reversed().thenComparing(lexeme
                    -> lexeme.getClearText(ElementType.LEXEME)));
        }
    }
}
