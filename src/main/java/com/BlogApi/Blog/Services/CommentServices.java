package com.BlogApi.Blog.Services;

import com.BlogApi.Blog.Entity.Comment;
import com.BlogApi.Blog.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServices {
    @Autowired
    private BlogRepository blogRepository;
    public ResponseEntity<List<Comment>> getAllCommentsService(String blog_id) {
        try {
            List<Comment> comm = new ArrayList<>();

            if (blog_id == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else
                blogRepository.getAllComments(Long.parseLong(blog_id)).forEach(comm::add);
            return new ResponseEntity<>(comm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Comment> addCommentService( Comment comment) {
        try {
            Comment new_comment = blogRepository.addComment(comment);
            if(new_comment!=null)
                return new ResponseEntity<>(new_comment, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Comment> updateCommentService( String comment_id, Comment new_comment) {
        Comment comment = blogRepository.getComment(Long.parseLong(comment_id));

        if (comment != null) {
            comment.setBody(new_comment.getBody());
            Comment updated_comment = blogRepository.updateComment(comment);
            return new ResponseEntity<>(updated_comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Comment> deleteBlogCommentService(String comment_id) {
        try {
            Comment deleted_comment = blogRepository.deleteComment(Long.parseLong(comment_id));
            if (deleted_comment!=null) {
                return new ResponseEntity<>(deleted_comment, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
