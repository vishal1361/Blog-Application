package com.BlogApi.Blog.Entity;

import java.util.List;
import com.BlogApi.Blog.Entity.Comment;
public class Blog {
    private long blog_id;
    private long user_id;
    private String title;
    private String author;
    private String body;
    private List<String> tagList;

    public Blog() {
        super();
    }

    public Blog(long blog_id, long user_id, String title, String author, String body, List<String>tagList) {
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.tagList = tagList;
    }

    public long getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(long blog_id) {
        this.blog_id = blog_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
