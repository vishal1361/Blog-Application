package com.BlogApi.Blog.Controller;

import com.BlogApi.Blog.Entity.Comment;
import com.BlogApi.Blog.Exception.ResourceNotFoundException;
import com.BlogApi.Blog.Services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RestController
public class CommentController {

    @Autowired
    private CommentServices commentServices;
    @GetMapping("/blogs/{blog_id}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable String blog_id) {
        return commentServices.getAllCommentsService(blog_id);
    }

    @PostMapping("/blogs/{blog_id}/comments")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) throws ResourceNotFoundException {
        return commentServices.addCommentService(comment);
    }

    @PatchMapping("/blogs/{blog_id}/comments/{comment_id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String blog_id, @PathVariable String comment_id, @RequestBody Comment new_comment) throws ResourceNotFoundException{
        return commentServices.updateCommentService(blog_id, comment_id, new_comment);
    }

    @DeleteMapping("/blogs/{blog_id}/comments/{comment_id}")
    public ResponseEntity<Comment> deleteBlogComment(@PathVariable String comment_id)throws ResourceNotFoundException {
        return commentServices.deleteBlogCommentService(comment_id);
    }

}
