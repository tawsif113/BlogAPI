package com.tawsif.CRUDBlog.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Schema(description = "Blog Entity")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the Blog.", example = "1")
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Schema(description = "Title of the Blog.", example = "My First Blog")
    private String title;

    @NotBlank(message = "Content is mandatory")
    @Column(length = 3000)
    @Schema(description = "Content of the Blog.", example = "This is my first blog. I am very excited to write this blog.")
    private String content;

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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
