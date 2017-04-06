package pl.seba.springtest.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = {"rec_id", "category", "content", "author_name"})
public final class Quote {

    @JsonProperty("rec_id")
    private Long id;

    private String category;

    private String content;

    @JsonProperty("author_name")
    private String authorName;

    public Quote() {
    }

    public Quote(Long id, String category, String content, String authorName) {
        this.id = id;
        this.category = category;
        this.content = content;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(id, quote.id) &&
                Objects.equals(category, quote.category) &&
                Objects.equals(content, quote.content) &&
                Objects.equals(authorName, quote.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, content, authorName);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
