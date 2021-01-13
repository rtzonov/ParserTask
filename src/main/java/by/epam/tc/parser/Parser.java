package by.epam.tc.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import by.epam.tc.entity.Attribute;
import by.epam.tc.entity.Element;
import by.epam.tc.parser.util.Stack;
import by.epam.tc.reader.Reader;

public class Parser implements by.epam.tc.skin.Parser {
    final char LESS = '<';
    final char MORE = '>';
    final String LESS_AND_SLASH = "</";
    final char SPACE = ' ';

    public Element nameParser(String text) {

        Stack<String> stack = new Stack();
        List<String> list = new ArrayList<>();
        List<Element> child = new ArrayList<>();

        char[] arr = text.toCharArray();
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == MORE && (i == arr.length - 1 || arr[i + 1] == LESS)) {
                String line = new String(Arrays.copyOfRange(arr, j, i + 1));
                j = i + 1;
                list.add(line);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            String begTag = line.substring(line.indexOf(LESS), line.indexOf(MORE) + 1);
            stack.push(begTag);
            if (begTag.indexOf(LESS_AND_SLASH) != -1) {
                Element element = parserAttribute(stack);
                element.setChildElements(child);
                child = new ArrayList<>();
                child.add(element);
            }

            if (!begTag.equals(line)) {
                String endTag = line.substring(line.indexOf(LESS_AND_SLASH));
                String content = line.substring(line.indexOf(MORE) + 1, line.indexOf(LESS_AND_SLASH));
                stack.push(content);
                stack.push(endTag);
                Element element = parserAttribute(stack);
                child.add(element);
            }

        }

        return child.get(0);
    }

    public Element parserAttribute(Stack<String> stack) {
        Element element = new Element();
        String tag = stack.pop();
        String tagName = tag.substring(tag.indexOf(LESS_AND_SLASH) + 2, tag.indexOf(MORE));
        element.setName(tagName);

        String content = stack.pop();
        if (!(content.charAt(0) == LESS) && !(content.charAt(content.length() - 1) == MORE)) {//если не тег то контент
            element.setContent(content);
            content = stack.pop();
        }
        if (content.indexOf(SPACE) != -1) {
            String attribute = content.substring(content.indexOf(SPACE), content.length() - 1);
            String[] arrAttribute = attribute.split("\"");
            List<Attribute> attributes = new ArrayList<>();
            for (int i = 0; i < arrAttribute.length; i += 2) {
                String name = arrAttribute[i].replaceAll("=", "");
                String value = arrAttribute[i + 1];
                Attribute allAttribute = new Attribute(name, value);
                attributes.add(allAttribute);
            }
            element.setAttributes(attributes);
        }
        return element;
    }

    public static void main(String[] args) {
        Parser pars = new Parser();
        Reader read = new Reader();
        System.out.println(pars.nameParser(read.reader()));
    }

}
