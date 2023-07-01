package com.BlogApi.Blog.Services;

import com.BlogApi.Blog.Entity.Comment;
import com.BlogApi.Blog.Exception.ResourceNotFoundException;
import com.BlogApi.Blog.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServices {
    @Autowired
    private BlogRepository blogRepository;
    public ResponseEntity<List<Comment>> getAllCommentsService(String blog_id) {
            return new ResponseEntity<>(blogRepository.getAllComments(Long.parseLong(blog_id)), HttpStatus.OK);
    }

    public ResponseEntity<Comment> addCommentService( Comment comment) throws ResourceNotFoundException {
        try {
            Comment new_comment = blogRepository.addComment(comment);
            return new ResponseEntity<>(new_comment, HttpStatus.CREATED);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("addCommentService", false, e.getMessage());
        }
    }

    public ResponseEntity<Comment> updateCommentService( String comment_id, Comment new_comment)throws ResourceNotFoundException {
        try{
            Comment comment = blogRepository.getComment(Long.parseLong(comment_id));
            comment.setBody(new_comment.getBody());
            Comment updated_comment = blogRepository.updateComment(comment);
            return new ResponseEntity<>(updated_comment, HttpStatus.OK);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("updateCommentService", false, "Comment with comment_id="+comment_id+" not found.");
        }
    }

    public ResponseEntity<Comment> deleteBlogCommentService(String comment_id)throws ResourceNotFoundException {
        try {
            Comment deleted_comment = blogRepository.deleteComment(Long.parseLong(comment_id));
            return new ResponseEntity<>(deleted_comment, HttpStatus.OK);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("deleteBlogCommentService", false, "Comment with comment_id="+comment_id+" not found.");
        }
    }
}
