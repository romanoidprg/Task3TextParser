package com.epam.jwd.text_parser.process;

import com.epam.jwd.text_parser.application.Application;
import com.epam.jwd.text_parser.text_elements.ElementType;
import com.epam.jwd.text_parser.text_elements.impl.TextHolder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextSorterTest {

    @Test
    public void sortParagraphsBySentenceQuantity() {
        TextHolder textHolder = Application.getTextHolder("src\\main\\resources\\input.txt");
        TextSorter.sortParagraphsBySentenceQuantity(textHolder);
        String expected =
                "    It is a 1201 established fact that a reader will be of a page when looking at its layout.\n" +
                        "    Bye.\n" +
                        "    It has survived - not only (five) centuries, but also the leap into 52 electronic typesetting, remaining 0 essentially 9 unchanged. It was popularized in the 5 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                        "    It is a long-established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using 78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.";
        assertEquals(expected, textHolder.getText());
    }

    @Test
    public void sortSentencesByElementLength() {
        TextHolder textHolder = Application.getTextHolder("src\\main\\resources\\testtexts\\sortSentencesByElementLength.txt");
        TextSorter.sortSentencesByElementLength(textHolder, ElementType.LEXEME);
        String expected = "    Hallo, I'm man. Lets go to play tennis, (golf), (football).";
        assertEquals(expected, textHolder.getText());
    }

    @Test
    public void sortLexemesBySymbol() {
        TextHolder textHolder = Application.getTextHolder("src\\main\\resources\\testtexts\\sortLexemesBySymbol.txt");
        TextSorter.sortLexemesBySymbol(textHolder, 'o');
        System.out.println(textHolder.getText());
        String expected = "    (football) (golf), go to Lets play tennis,. Hallo, I'm man.";
        assertEquals(expected, textHolder.getText());
    }
}