package com.BlogApi.Blog.Services;

import com.BlogApi.Blog.Entity.Blog;
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
        List<Blog> x = this.blogRepository.getBlogsByTagName(tag_name);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    public ResponseEntity<Blog> updateTagsOfBlogService(String blog_id, Blog blog) {
        Blog b = blogRepository.updateTagsOfBlog(Long.parseLong(blog_id), blog.getTagList());
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getAllTagsService() {
        return new ResponseEntity<>(blogRepository.getAllTags(), HttpStatus.OK);
    }
}
