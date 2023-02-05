package be.vinci;

import java.util.Arrays;
import java.util.Objects;

public class Text {
    private int id;
    private String content;
    private final static String[] LEVELS = {"easy","medium","hard"};
    private String level;

    public Text() {
    }

    public Text(int id, String content, String level) {
        this.id = id;
        this.content = content;
        setLevel(level);
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLevel(String level) {
        this.level = Arrays.stream(LEVELS).filter(l -> l.equals(level))
                .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Text [id=" + id + ", content=" + content + ", level=" + level + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return id == text.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
