package com.francislzf.blog.bean;

import org.springframework.data.annotation.Transient;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String nickname;
    private Long blogId; //对应的博客id
    private String blogTitle; //对应的博客标题
    private String avatar;
    private Date createTime;
    private Boolean blogHolder;//是否是博主
    private Long parentCommentId;
    private Boolean adminComment;
    private String email;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //回复评论
    @Transient
    private List<Comment> replyComments = new ArrayList<>();//一条父评论对应一个回复评论的集合
    @Transient
    private Comment parentComment; //一条回复评论持有父评论对象
    @Transient
    private Blog blog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Boolean getBlogHolder() {
        return blogHolder;
    }

    public void setBlogHolder(Boolean blogHolder) {
        this.blogHolder = blogHolder;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public void setAdminComment(Boolean adminComment){ this.adminComment = adminComment; }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                ", blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blogHolder=" + blogHolder +
                ", parentCommentId=" + parentCommentId +
                ", adminComment=" + adminComment +
                ", email='" + email + '\'' +
                ", ip='" + ip + '\'' +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                '}';
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
