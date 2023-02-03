package be.vinci;

import java.util.Objects;

public class Text {
    private int id;
    private String content;
    private Text level;

    public Text() {
    }

    public Text(int id, String content, Text level) {
        this.id = id;
        this.content = content;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Text getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLevel(Text level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", level='" + level + '\'' +
                '}';
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
        return Objects.hash(id);
    }

    public enum Level{
        EASY("easy"),MEDIUM("medium"),HARD("hard");
        private String level;
        Level(String level) {
            this.level = level;
        }

        public String getLevel() {
            return level;
        }
    }
}
