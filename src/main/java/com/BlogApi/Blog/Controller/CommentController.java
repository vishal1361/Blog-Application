package com.BlogApi.Blog.Controller;

import com.BlogApi.Blog.Entity.Comment;
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
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return commentServices.addCommentService(comment);
    }

    @PutMapping("/blogs/{blog_id}/comments/{comment_id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String comment_id, @RequestBody Comment new_comment) {
        return commentServices.updateCommentService(comment_id, new_comment);
    }

    @DeleteMapping("/blogs/{blog_id}/comments/{comment_id}")
    public ResponseEntity<Comment> deleteBlogComment(@PathVariable String comment_id) {
        return commentServices.deleteBlogCommentService(comment_id);
    }

}
