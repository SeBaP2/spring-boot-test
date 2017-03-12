package pl.seba.springtest.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @JsonProperty("ID")
    private Long id;
    private String title;
    private String content;
    private String link;

    public Quote() {
    }

    public Quote(Long id, String title, String content, String link) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(id, quote.id) &&
                Objects.equals(title, quote.title) &&
                Objects.equals(content, quote.content) &&
                Objects.equals(link, quote.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, link);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
