package com.epam.jwd.text_parser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class TextRecieverFromFile {
    private final static Logger logger = LoggerFactory.getLogger(TextRecieverFromFile.class);

    public static String recieveText(String filePath) {
        StringBuilder text = new StringBuilder("");
        try {
            String[] s = Files.readAllLines(FileSystems.getDefault().getPath(filePath)).toArray(new String[0]);
            for (int i = 0; i < s.length; i++) {
                if (i > 0) {
                    text.append("\n");
                }
                text.append(s[i]);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return text.toString();
    }
}
