package com.epam.jwd.text_parser.text_elements;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TextParticleTest {

    @Test
    public void getText() {
        TextParticle textParticle = new TextParticle("TextParticle", ElementType.LEXEME);
        assertEquals("TextParticle", textParticle.getText());
    }

    @Test
    public void getClearText() {
        TextParticle textParticle = new TextParticle("TextParticle", ElementType.LEXEME);
        assertEquals("TextParticle", textParticle.getClearText(ElementType.LEXEME));
        assertNotEquals("TextParicle", textParticle.getClearText(ElementType.LEXEME));
        assertNotEquals("TextParticle", textParticle.getClearText(ElementType.WITH_WORD_SYMBOL));
    }

    @Test
    public void getElements() {
        TextParticle textParticle = new TextParticle("TextParticle", ElementType.LEXEME);
        assertEquals(null, textParticle.getElements(0));
    }
}