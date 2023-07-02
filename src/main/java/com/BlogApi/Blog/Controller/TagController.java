package com.BlogApi.Blog.Controller;

import com.BlogApi.Blog.Entity.Blog;
import com.BlogApi.Blog.Exception.ResourceNotFoundException;
import com.BlogApi.Blog.Services.TagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RestController
public class TagController {
    @Autowired
    private TagServices tagServices;

    @GetMapping("/blogs/tags/{tag_name}")
    public ResponseEntity<List<Blog>> getBlogsByTagName(@PathVariable String tag_name) {
        return tagServices.getBlogsByTagNameService(tag_name);
    }


    @GetMapping("/blogs/tags")
    public ResponseEntity<List<String>> getAllTags() {
        return tagServices.getAllTagsService();
    }
}
