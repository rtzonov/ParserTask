package by.epam.tc.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Element {
    private String name;
    private List<Element> childElements = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();
    private String content;

    public Element(){}

    public Element(String name, List<Element> childElements, List<Attribute> attributes, String content) {
        this.name = name;
        this.childElements = childElements;
        this.attributes = attributes;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getChildElements() {
        return childElements;
    }

    public void setChildElements(List<Element> childElements) {
        this.childElements = childElements;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return name.equals(element.name) &&
                childElements.equals(element.childElements) &&
                attributes.equals(element.attributes) &&
                content.equals(element.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, childElements, attributes, content);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Element{");
        sb.append("name='").append(name).append('\'');
        sb.append(", childElements=").append(childElements);
        sb.append(", attributes=").append(attributes);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
