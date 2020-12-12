package com.francislzf.blog.controller;

import com.francislzf.blog.bean.*;
import com.francislzf.blog.bean.helpbean.PreviewBlog;
import com.francislzf.blog.bean.helpbean.RecommendPreviewBlog;
import com.francislzf.blog.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    //首页显示
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, HttpServletRequest request) {
        PageHelper.startPage(pageNum, 5);
        List<PreviewBlog> previewBlogs = blogService.getPreviewBlog();//获得首页的博客预览信息,并按访问量从高到低排序
        List<Type> types = typeService.getPreViewType(4); //获得博客数量前四的type
        List<Tag> tags = tagService.getPreViewTag(9);//获得博客数量前9的tag
        List<RecommendPreviewBlog> recommendPreviewBlogs = blogService.getRecommendPreviewBlog(4);
        PageInfo<PreviewBlog> pageInfo = new PageInfo<>(previewBlogs);
        model.addAttribute("page", pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
        model.addAttribute("recommendBlogs", recommendPreviewBlogs);
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    //点进首页的一篇博客
    @GetMapping("/blog/{id}")
    public String showBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getMdBlogById(id);
        User user = userService.getUserById(blog.getUserId());
        List<Comment> comments = commentService.getCommentsByBlogId(id);//获得该博客的评论
        model.addAttribute("blog", blog);
        model.addAttribute("user", user);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
