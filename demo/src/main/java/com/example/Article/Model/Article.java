package com.example.Article.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Article {

    @NotEmpty(message = "id is required")
    private String id;

    @NotEmpty(message = "title is required")
    @Size(max = 100, message = "title length must be less than 100")
    private String title;

    @NotEmpty(message = "author is required")
    @Size(min = 4, max = 20, message = "author is must be 4 to 20")
    private String author;

    @NotEmpty(message = "content is required")
    @Size(min = 200, message = "content is must be at least 200")
    private String content;

    @NotEmpty(message = "category is required")
    @Pattern(regexp = "(politics|sports|technology)", message = "category must be either: politics or sports or technology")
    private String category;

    @NotEmpty(message = "imageUrl is required")
    private String imageUrl;

    @NotEmpty(message = "is published is required")
    @AssertFalse( message = "published must be false")
    private boolean isPublished;

    @NotNull(message = "publish date is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "publish date must be past or present")
    private LocalDateTime publishDate;


}
