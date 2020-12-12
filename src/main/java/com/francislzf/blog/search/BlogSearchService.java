package com.francislzf.blog.search;

import java.util.List;

public interface BlogSearchService {
    List<BlogSearchBean> searchBlog(String searchContent);
}
