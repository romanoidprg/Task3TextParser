package com.epam.jwd.text_parser.text_elements;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementHolder implements TextElement {
    protected List<TextElement> components = new ArrayList<>();

    public void addElement(TextElement element) {
        components.add(element);
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder();
        for (TextElement e : components) {
            text.append(e.getText());
        }
        return text.toString();
    }

    @Override
    public String getClearText(ElementType elementType) {
        StringBuilder text = new StringBuilder();
        for (TextElement e : components) {
            text.append(e.getClearText(elementType));
        }
        return text.toString();
    }

    @Override
    public List<TextElement> getElements(int level) {
        List<TextElement> list = new ArrayList<>();
        if (level == 0) {
            list = components;
        } else if (level > 0) {
            level--;
            for (TextElement el : components) {
                list.addAll(el.getElements(level));
            }
        }
        return list;
    }
}
