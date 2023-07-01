package com.BlogApi.Blog.Controller;

import com.BlogApi.Blog.Entity.Blog;
import com.BlogApi.Blog.Exception.BlogIdNotFoundException;
import com.BlogApi.Blog.Exception.ExistingTitleException;
import com.BlogApi.Blog.Services.BlogPostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"}, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RestController
public class BlogPostController {
    @Autowired
    private BlogPostServices blogServices;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getBlogPosts(@RequestParam("keyword") Optional<String> keyword, @RequestParam("blog_id") Optional<String> blog_id) {
        if(!keyword.isPresent() && !blog_id.isPresent()) {
            return blogServices.getBlogPostsService();
        }
        if(keyword.isPresent()) {
            return blogServices.getBlogPostsService(keyword.get());
        }
        List<Blog> temp = new ArrayList<>();
        Blog b = blogServices.getBlogPostsService(Long.parseLong(blog_id.get()));
        if(b!=null) {
            temp.add(b);
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    @RequestMapping(path = "/blogs", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<Blog> createBlogPost(@RequestBody Blog blog) throws ExistingTitleException {
        return blogServices.createBlogPostService(blog);
    }


    @PatchMapping("/blogs/{blog_id}")
    public ResponseEntity<Blog> updateBlogPost(@PathVariable String blog_id, @RequestBody Blog new_blog) throws BlogIdNotFoundException {
        return blogServices.updateBlogPostService(blog_id, new_blog);
    }
    @DeleteMapping("/blogs/{blog_id}")
    public ResponseEntity<Blog> deleteBlogPost(@PathVariable String blog_id) throws BlogIdNotFoundException {
        return blogServices.deleteBlogPostService(blog_id);
    }
}
