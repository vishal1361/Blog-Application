package com.BlogApi.Blog.Services;
import com.BlogApi.Blog.Entity.Blog;
import com.BlogApi.Blog.Exception.BlogIdNotFoundException;
import com.BlogApi.Blog.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.BlogApi.Blog.Exception.ExistingTitleException;
import java.util.ArrayList;
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
            List<Blog> blogs = new ArrayList<>();
            blogRepository.getBlogPosts(title).forEach(blogs::add);
            return new ResponseEntity<>(blogs, HttpStatus.OK);
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

    public ResponseEntity<Blog> updateBlogPostService( String blog_id,  Blog new_blog) throws BlogIdNotFoundException {
        try{
            Blog blog = blogRepository.getBlogPostsById(Long.parseLong(blog_id));
            blog.setUser_id(new_blog.getUser_id());
            if(blog.getTitle()!=null)blog.setTitle(new_blog.getTitle());
            if(blog.getBody()!=null)blog.setBody(new_blog.getBody());
            if(blog.getAuthor()!=null)blog.setAuthor(new_blog.getAuthor());
            Blog updated_blog  = blogRepository.updateBlogPost(blog);
            return new ResponseEntity<>(updated_blog, HttpStatus.OK);
        }
        catch(Exception e) {
            throw new BlogIdNotFoundException("updateBlogPostService", false, e.getMessage());
        }
    }

    public ResponseEntity<Blog> deleteBlogPostService( String blog_id) throws BlogIdNotFoundException {
        try{
            Blog deleted_blog = blogRepository.deleteBlogPost(Long.parseLong(blog_id));
            return new ResponseEntity<>(deleted_blog, HttpStatus.OK);
        }
        catch(Exception e) {
            throw new BlogIdNotFoundException("deleteBlogPostService", false, e.getLocalizedMessage());
        }
    }

}
