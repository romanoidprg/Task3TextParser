package com.epam.jwd.text_parser;

import com.epam.jwd.text_parser.application.ApplicationTest;
import com.epam.jwd.text_parser.parcers.impl.LexemeParserTest;
import com.epam.jwd.text_parser.parcers.impl.ParagraphParserTest;
import com.epam.jwd.text_parser.parcers.impl.SentenceParserTest;
import com.epam.jwd.text_parser.parcers.impl.WholeTextParserTest;
import com.epam.jwd.text_parser.process.ExprCalcTest;
import com.epam.jwd.text_parser.process.TextSorterTest;
import com.epam.jwd.text_parser.service.TextRecieverFromFileTest;
import com.epam.jwd.text_parser.text_elements.ElementHolderTest;
import com.epam.jwd.text_parser.text_elements.TextParticleTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ElementHolderTest.class,
        TextParticleTest.class,
        TextRecieverFromFileTest.class,
        ExprCalcTest.class,
        TextSorterTest.class,
        WholeTextParserTest.class,
        SentenceParserTest.class,
        ParagraphParserTest.class,
        LexemeParserTest.class,
        ApplicationTest.class,
})

public class AllTests {
}
