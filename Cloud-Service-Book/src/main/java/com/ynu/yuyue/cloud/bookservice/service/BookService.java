package com.ynu.yuyue.cloud.bookservice.service;

import com.ynu.yuyue.cloud.bookservice.domain.Book;

/**
 * Created by yuyue on 2017/9/7.
 */

public interface BookService {
    Book getOneBook(Integer id);
}
