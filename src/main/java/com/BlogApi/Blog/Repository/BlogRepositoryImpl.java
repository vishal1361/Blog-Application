package com.BlogApi.Blog.Repository;

import com.BlogApi.Blog.Entity.Blog;
import com.BlogApi.Blog.Entity.Comment;
import com.BlogApi.Blog.Entity.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogRepositoryImpl implements BlogRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String table_name = "Blog_Details";
    private final String comment_table = "Comment_Details";
    private final String tag_table = "Tag_Details";
    private final String tag_collection = "Tag_Collection";

    public List<String> getTagOfBlog(long blog_id) {
        String sql = "Select tag_name from Tag_Details where blog_id = ?";
        List <Tag> tags = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Tag.class), blog_id);
        List<String> tagList = new ArrayList<>();
        for(Tag t : tags) {
            tagList.add(t.getTag_name());
        }
        return tagList;
    }
    public int insertIntoTagTable(List<String>tagList, long blog_id) {
        int c = 0;
        for(String tag_name : tagList) {
            c += jdbcTemplate.update("INSERT INTO " + tag_table + " (tag_name, blog_id) VALUES(?, ?)", tag_name, blog_id);
        }
        return c;
    }
    @Override
    public List<Blog> getBlogPosts() {
        List<Blog> blogList = jdbcTemplate.query("SELECT * from "+table_name, BeanPropertyRowMapper.newInstance(Blog.class));
        for(Blog b : blogList) {
            b.setTagList(getTagOfBlog(b.getBlog_id()));
        }
        return blogList;
    }
    @Override
    public List<Blog> getBlogPosts(String title) {
        String q = "SELECT * from "+ table_name + " WHERE title ILIKE '%" + title + "%'";
        List<Blog> blogList = jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Blog.class));
        for(Blog b : blogList) {
            b.setTagList(getTagOfBlog(b.getBlog_id()));
        }
        return blogList;
    }
    @Override
    public Blog getBlogPostsById(long blog_id) {
        try {
            Blog blog = jdbcTemplate.queryForObject("SELECT * FROM "+table_name+" WHERE blog_id = ?",
                    BeanPropertyRowMapper.newInstance(Blog.class), blog_id);
            blog.setTagList(getTagOfBlog(blog.getBlog_id()));
            return blog;
        }
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }



    @Override
    public Blog createBlogPost(Blog blog) {
        Blog b = jdbcTemplate.queryForObject("INSERT INTO " + table_name + " (user_id, title, author, body) VALUES(?,?,?,?) returning blog_id, user_id, title, author, body",
                BeanPropertyRowMapper.newInstance(Blog.class), blog.getUser_id(), blog.getTitle(), blog.getAuthor(), blog.getBody());
        b.setTagList(blog.getTagList());
        insertIntoTagTable(blog.getTagList(), b.getBlog_id());
        return b;
    }
    @Override
    public List<Blog> getBlogsByTagName(String tag) {
        String sql = "SELECT " +
                "Blog_Details.blog_id, " +
                "Blog_Details.user_id, " +
                "Blog_Details.title, " +
                "Blog_Details.author, " +
                "Blog_Details.body FROM " +
                "Blog_Details INNER JOIN Tag_Details ON " +
                "Tag_Details.tag_name = '"+tag+"' AND " +
                "Blog_Details.blog_id = Tag_Details.blog_id ";
        List<Blog> blogs = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Blog.class));
        for(Blog b : blogs) {
            b.setTagList(getTagOfBlog(b.getBlog_id()));
        }
        return blogs;
    }

    @Override
    public Blog updateTagsOfBlog(long blog_id, List<String>new_tag) {
        String sql = "Delete from "+tag_table+" where blog_id = ?";
        jdbcTemplate.update(sql, blog_id);
        insertIntoTagTable(new_tag, blog_id);
        return getBlogPostsById(blog_id);
    }
    @Override
    public List<String> getAllTags() {
        String sql = "Select tag_name from " + tag_collection;
        List<String> tagList = jdbcTemplate.queryForList(sql, String.class);
        return tagList;
    }

    @Override
    public Blog deleteBlogPost(long blog_id) {
        Blog b = jdbcTemplate.queryForObject("DELETE FROM "+table_name +" WHERE blog_id = ? returning blog_id, user_id, title, author, body",
                BeanPropertyRowMapper.newInstance(Blog.class), blog_id);

        return b;
    }

    @Override
    public Blog updateBlogPost(Blog blog) {
        Blog b = jdbcTemplate.queryForObject("UPDATE "+table_name+" SET title=?, author=?,  body=? WHERE blog_id = ? returning blog_id, user_id, title, author, body",
                BeanPropertyRowMapper.newInstance(Blog.class), blog.getTitle(), blog.getAuthor(), blog.getBody(), blog.getBlog_id());
        b.setTagList(getTagOfBlog(b.getBlog_id()));
        return b;
    }

    @Override
    public List<Comment> getAllComments(long blog_id) {
        String q = "SELECT * from "+ comment_table + " WHERE blog_id = "+ blog_id;
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    @Override
    public Comment getComment(long blog_id, long comment_id) {
        Comment comment = jdbcTemplate.queryForObject("SELECT * FROM "+comment_table+" WHERE comment_id = ?, blog_id = ?",
                    BeanPropertyRowMapper.newInstance(Comment.class), comment_id, blog_id);
        return comment;
    }

    @Override
    public Comment addComment(Comment comment) {
        Comment new_comment = jdbcTemplate.queryForObject("INSERT INTO " + comment_table + " (user_id, blog_id, body) VALUES(?,?,?) returning comment_id, user_id, blog_id, body",
                BeanPropertyRowMapper.newInstance(Comment.class), comment.getUser_id(), comment.getBlog_id(), comment.getBody());
        return new_comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment updated_comment = jdbcTemplate.queryForObject("UPDATE "+comment_table+" SET body=? WHERE comment_id = ? returning comment_id, user_id, blog_id, body",
                BeanPropertyRowMapper.newInstance(Comment.class), comment.getBody(), comment.getComment_id());
        return updated_comment;
    }

    @Override
    public Comment deleteComment(long comment_id) {
        Comment deleted_comment = jdbcTemplate.queryForObject("DELETE FROM "+comment_table +" WHERE comment_id=? returning comment_id, user_id, blog_id, body",
                BeanPropertyRowMapper.newInstance(Comment.class),comment_id);
        return deleted_comment;
    }


}
