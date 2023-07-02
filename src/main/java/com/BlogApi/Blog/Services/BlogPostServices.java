package com.BlogApi.Blog.Services;
import com.BlogApi.Blog.Entity.Blog;
import com.BlogApi.Blog.Exception.ResourceNotFoundException;
import com.BlogApi.Blog.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.BlogApi.Blog.Exception.ExistingTitleException;

import java.util.List;
@Service
public class BlogPostServices {
    @Autowired
    private BlogRepository blogRepository;
    public ResponseEntity<List<Blog>> getBlogPostsService() {
        return new ResponseEntity<>(this.blogRepository.getBlogPosts(), HttpStatus.OK);
    }

    public Blog getBlogPostsService(long blog_id) {
        return blogRepository.getBlogPostsById(blog_id);
    }

    public ResponseEntity<List<Blog>> getBlogPostsService(String title) {
            return new ResponseEntity<>(blogRepository.getBlogPosts(title), HttpStatus.OK);
    }

    public ResponseEntity<Blog> createBlogPostService(@RequestBody Blog blog) throws ExistingTitleException{
        try{
            Blog inserted_blog = blogRepository.createBlogPost(blog);
            return ResponseEntity.status(HttpStatus.CREATED).body(inserted_blog);
        }
        catch(Exception e) {
            throw new ExistingTitleException("createBlogPostService", e.getMessage(), false);
        }
    }

    public ResponseEntity<Blog> updateBlogPostService( String blog_id,  Blog new_blog) throws ResourceNotFoundException {
        try{
            Blog blog = blogRepository.getBlogPostsById(Long.parseLong(blog_id));
            if(blog.getTitle().equals(new_blog.getTitle())==false || blog.getAuthor().equals(new_blog.getAuthor())==false || blog.getBody().equals(new_blog.getBody())==false) {
                if(new_blog.getTitle()!=null)blog.setTitle(new_blog.getTitle());
                if(new_blog.getBody()!=null)blog.setBody(new_blog.getBody());
                if(new_blog.getAuthor()!=null)blog.setAuthor(new_blog.getAuthor());
                blog = blogRepository.updateBlogPost(blog);
            }

            if(new_blog.getTagList()!= null && blog.getTagList().equals(new_blog.getTagList()) == false) {
                blog = blogRepository.updateTagsOfBlog(Long.parseLong(blog_id), new_blog.getTagList());
            }
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("updateBlogPostService", false, e.getMessage());
        }
    }

    public ResponseEntity<Blog> deleteBlogPostService( String blog_id) throws ResourceNotFoundException {
        try{
            Blog deleted_blog = blogRepository.deleteBlogPost(Long.parseLong(blog_id));
            return new ResponseEntity<>(deleted_blog, HttpStatus.OK);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("deleteBlogPostService", false, "Blog with blog_id="+blog_id+" not found!.");
        }
    }

}
