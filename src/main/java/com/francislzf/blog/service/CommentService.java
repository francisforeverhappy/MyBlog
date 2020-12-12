package com.francislzf.blog.service;



import com.francislzf.blog.bean.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBlogId(Long id);
    Integer saveComment(Comment comment);
}
