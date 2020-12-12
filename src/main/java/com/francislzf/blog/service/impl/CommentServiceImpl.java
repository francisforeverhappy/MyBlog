package com.francislzf.blog.service.impl;


import com.francislzf.blog.bean.Comment;
import com.francislzf.blog.mapper.CommentMapper;
import com.francislzf.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByBlogId(Long id) {
        List<Comment> comments = commentMapper.getCommentsByBlogId(id);
        for (Comment comment : comments) {
            List<Comment> replyComments = commentMapper.getReplyCommentsBycommentId(comment.getBlogId(), comment.getId());
            comment.setReplyComments(replyComments); //将拿到的回复评论设置给评论
            for (Comment replyComment : replyComments) {
                replyComment.setParentComment(comment);//再设置父评论给查出来的回复评论
            }
        }
        return comments;
    }
    @Transactional
    @Override
    public Integer saveComment(Comment comment) {
        //Long parentCommentId = comment.getParentCommentId();
//        if (parentCommentId != -1) {
//            comment.setParentComment(commentMapper.selectByPrimaryKey(parentCommentId));
//        } else {
//            comment.setParentComment(null);
//        }
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
