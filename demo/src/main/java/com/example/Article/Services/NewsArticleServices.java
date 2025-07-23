package com.example.Article.Services;

import com.example.Article.Model.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsArticleServices {
    ArrayList<Article> articles = new ArrayList<>();



    public ArrayList<Article> getArticles(){
        return articles;
    }

    public void addArticle(Article article){
        articles.add(article);
    }

    public boolean updateArticle(String id, Article article){
        int index = findArticle(id);
        if (index == -1){
            return false;
        }
        articles.set(index, article);
        return true;
    }

    public boolean deleteArticle(String id){
        int index = findArticle(id);
        if (index == -1){
            return false;
        }
        articles.remove(index);
        return true;
    }

    public boolean publish(String id){
        int index = findArticle(id);
        if (index == -1){
            return false;
        }
        articles.get(index).setPublished(true);
        return true;
    }


    public ArrayList<Article> getPublishedArticles(){
        ArrayList<Article> published = new ArrayList<>();
        for(Article a: articles){
            if (a.isPublished()){
                published.add(a);
            }
        }
        return published;
    }

    public ArrayList<Article> getArticleByCategory(String category){
        ArrayList<Article> articleByCategory = new ArrayList<>();
        for(Article a: articles){
            if (a.getCategory().equals(category)){
                articleByCategory.add(a);
            }
        }
        return articleByCategory;
    }





    private int findArticle(String id){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }


}



