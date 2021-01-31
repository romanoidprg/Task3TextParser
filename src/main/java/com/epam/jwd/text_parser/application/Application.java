package com.epam.jwd.text_parser.application;

import com.epam.jwd.text_parser.parcers.Parser;
import com.epam.jwd.text_parser.parcers.impl.LexemeParser;
import com.epam.jwd.text_parser.parcers.impl.ParagraphParser;
import com.epam.jwd.text_parser.parcers.impl.SentenceParser;
import com.epam.jwd.text_parser.parcers.impl.WholeTextParser;
import com.epam.jwd.text_parser.service.TextRecieverFromFile;
import com.epam.jwd.text_parser.text_elements.impl.TextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    public static TextHolder getTextHolder(String filepath) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        TextHolder wholeText = new TextHolder();
        Parser lexemeParser = new LexemeParser();
        Parser sentenceParser = new SentenceParser(lexemeParser);
        Parser paragraphParser = new ParagraphParser(sentenceParser);
        Parser wholeTextParser = new WholeTextParser(paragraphParser);
        String text = TextRecieverFromFile.recieveText(filepath);
        wholeTextParser.parse(wholeText, text);
        logger.info("\n" + wholeText.getText());
        return wholeText;
    }
}
