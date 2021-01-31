package com.epam.jwd.text_parser.text_elements;

import com.epam.jwd.text_parser.text_elements.impl.LexemeHolder;
import com.epam.jwd.text_parser.text_elements.impl.ParagraphHolder;
import com.epam.jwd.text_parser.text_elements.impl.SentenceHolder;
import com.epam.jwd.text_parser.text_elements.impl.TextHolder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class ElementHolderTest {

    @Test
    public void addElement_WhenAdd_ComponentsShouldIcrease() {
        ElementHolder elementHolder = Mockito.spy(ElementHolder.class);
        TextParticle textParticle = new TextParticle("Text", ElementType.WORD);
        assertEquals(0, elementHolder.components.size());
        elementHolder.addElement(textParticle);
        assertEquals(1, elementHolder.components.size());
    }

    @Test
    public void getText_WhenDo_ShouldReceiveTextWithDelimeters() {
        TextParticle textParticle = new TextParticle("Text1", ElementType.WORD);
        String expected = "Text1";
        assertEquals(expected, textParticle.getText());

        ElementHolder lexeme = new LexemeHolder();
        lexeme.addElement(textParticle);
        expected = "Text1 ";
        assertEquals(expected, lexeme.getText());
        expected = "Text1";
        assertNotEquals(expected, lexeme.getText());

        ElementHolder sentence = new SentenceHolder("!");
        sentence.addElement(lexeme);
        expected = "Text1! ";
        assertEquals(expected, sentence.getText());
        expected = "Text1";
        assertNotEquals(expected, sentence.getText());

        ElementHolder paragraph = new ParagraphHolder();
        paragraph.addElement(sentence);
        expected = "    Text1!\n";
        assertEquals(expected, paragraph.getText());
        expected = "Text1";
        assertNotEquals(expected, paragraph.getText());

        ElementHolder text = new TextHolder();
        text.addElement(paragraph);
        expected = "    Text1!";
        assertEquals(expected, text.getText());
        expected = "Text1";
        assertNotEquals(expected, text.getText());

    }

    @Test
    public void getClearText_WhenDo_ShouldRecieveTextWithoutDelimeters() {
        TextParticle textParticle = new TextParticle("Text1", ElementType.WORD);
        String expected = "Text1";
        assertEquals(expected, textParticle.getText());

        ElementHolder lexeme = new LexemeHolder();
        lexeme.addElement(textParticle);
        expected = "Text1";
        assertEquals(expected, lexeme.getClearText(ElementType.WORD));
        expected = "Text1 ";
        assertNotEquals(expected, lexeme.getClearText(ElementType.WORD));

        ElementHolder sentence = new SentenceHolder("!");
        sentence.addElement(lexeme);
        expected = "Text1";
        assertEquals(expected, sentence.getClearText(ElementType.WORD));
        expected = "Text1!";
        assertNotEquals(expected, sentence.getClearText(ElementType.WORD));

        ElementHolder paragraph = new ParagraphHolder();
        paragraph.addElement(sentence);
        expected = "Text1";
        assertEquals(expected, paragraph.getClearText(ElementType.WORD));
        expected = "    Text1";
        assertNotEquals(expected, paragraph.getClearText(ElementType.WORD));

        ElementHolder text = new TextHolder();
        text.addElement(paragraph);
        expected = "Text1";
        assertEquals(expected, text.getClearText(ElementType.WORD));
        expected = "    Text1";
        assertNotEquals(expected, text.getClearText(ElementType.WORD));
    }

    @Test
    public void getElements_WhenDo_ShouldRecieveElementsList() {
        TextParticle textParticle1 = new TextParticle("Text1", ElementType.WORD);
        TextParticle textParticle2 = new TextParticle("Text2", ElementType.WORD);
        TextParticle textParticle3 = new TextParticle("Text3", ElementType.WORD);
        ElementHolder lexeme = new LexemeHolder();
        lexeme.addElement(textParticle1);
        lexeme.addElement(textParticle2);
        lexeme.addElement(textParticle3);
        List<TextElement> expectedList = new ArrayList<>();
        expectedList.add(textParticle1);
        expectedList.add(textParticle2);
        expectedList.add(textParticle3);
        assertEquals(expectedList, lexeme.getElements(0));
    }
}