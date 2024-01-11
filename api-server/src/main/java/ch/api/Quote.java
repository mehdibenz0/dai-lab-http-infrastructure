package ch.api;
import java.util.Objects;

public class Quote {
    private String id;
    private String author;
    private String content;

    public Quote() {
    }
    
    public Quote(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        Quote quote = (Quote) o;
        return Objects.equals(id, quote.id) &&
                Objects.equals(author, quote.author) &&
                Objects.equals(content, quote.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}