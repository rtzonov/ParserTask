package by.epam.tc.skin;

import by.epam.tc.entity.Element;
import by.epam.tc.parser.util.Stack;

public interface Parser {
    Element nameParser(String text);
    Element parserAttribute(Stack<String> stack);
}
