package com.francislzf.blog.mapper;


import com.francislzf.blog.bean.Blog;
import com.francislzf.blog.bean.Comment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    List<Comment> getCommentsByBlogId(@Param("blogId") Long id);

    List<Comment> getReplyCommentsBycommentId(@Param("blogId") Long blogId, @Param("commentId") Long commentId);

    Integer saveComment(@Param("comment") Comment comment);
}
