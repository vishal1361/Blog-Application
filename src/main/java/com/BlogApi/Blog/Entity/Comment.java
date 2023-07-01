package com.BlogApi.Blog.Entity;

public class Comment {
    private long comment_id;
    private long user_id;
    private long blog_id;
    private String body;
    public Comment() {
        super();
    }
    public Comment(long comment_id, long user_id, long blog_id, String body) {
        super();
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.blog_id = blog_id;
        this.body = body;
    }

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(long blog_id) {
        this.blog_id = blog_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
