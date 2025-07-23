package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.Article;
import com.example.demo.Services.NewsArticleServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleServices newsArticleServices;


    @GetMapping("/get")
    public ResponseEntity<?> getAllArticles(){
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleServices.getArticles());
    }

    @PostMapping("/create")
    public ResponseEntity<?> addArticle(@Valid@RequestBody Article article, Errors error){
        if (error.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getFieldError().getDefaultMessage());
        }else {
            newsArticleServices.addArticle(article);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("article add successfully"));
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable String id, @Valid@RequestBody Article article, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getFieldError().getDefaultMessage());
        }else if (newsArticleServices.updateArticle(id, article)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("article updated successfully"));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("article not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id){
        if (newsArticleServices.deleteArticle(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("article deleted successfully"));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("article not found"));
        }
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publishArticle(@PathVariable String id){
        if (newsArticleServices.publish(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("article is published successfully"));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("article is not found"));
        }
    }


    @GetMapping("/publish")
    public ResponseEntity<?> getPublishedArticles(){
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleServices.getPublishedArticles());
    }


    @GetMapping("/get/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleServices.getArticleByCategory(category));
    }



}
