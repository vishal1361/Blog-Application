package com.BlogApi.Blog.Repository;
import java.util.List;
import com.BlogApi.Blog.Entity.Blog;
import com.BlogApi.Blog.Entity.Comment;
public interface BlogRepository {
    public int insertIntoTagTable(List<String>tagList, long blog_id);
    public List<Blog> getBlogPosts();
    public List<Blog> getBlogPosts(String title);
    public Blog getBlogPostsById(long blog_id);
    public Blog createBlogPost(Blog blog);
    public Blog deleteBlogPost(long blog_id);
    public Blog updateBlogPost(Blog blog);
    public List<Comment> getAllComments(long blog_id);
    public Comment getComment(long comment_id);
    public Comment addComment(Comment comment);
    public Comment updateComment(Comment comment);
    public Comment deleteComment(long comment_id);
    public List<Blog> getBlogsByTagName(String tag);
    public Blog updateTagsOfBlog(long blog_id, List<String>new_tag);
    public List<String> getAllTags();
}
