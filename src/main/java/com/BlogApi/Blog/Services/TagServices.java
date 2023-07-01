package com.BlogApi.Blog.Services;

import com.BlogApi.Blog.Entity.Blog;
import com.BlogApi.Blog.Exception.ResourceNotFoundException;
import com.BlogApi.Blog.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServices {
    @Autowired
    private BlogRepository blogRepository;
    public ResponseEntity<List<Blog>> getBlogsByTagNameService(String tag_name) {
        return new ResponseEntity<>(this.blogRepository.getBlogsByTagName(tag_name), HttpStatus.OK);
    }

    public ResponseEntity<Blog> updateTagsOfBlogService(String blog_id, Blog blog)throws ResourceNotFoundException {
        try{
            return new ResponseEntity<>(blogRepository.updateTagsOfBlog(Long.parseLong(blog_id), blog.getTagList()), HttpStatus.OK);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("updateTagsOfBlogService", false, "Blog with blog_id="+blog_id+" not found!");
        }
    }

    public ResponseEntity<List<String>> getAllTagsService() {
        return new ResponseEntity<>(blogRepository.getAllTags(), HttpStatus.OK);
    }
}
