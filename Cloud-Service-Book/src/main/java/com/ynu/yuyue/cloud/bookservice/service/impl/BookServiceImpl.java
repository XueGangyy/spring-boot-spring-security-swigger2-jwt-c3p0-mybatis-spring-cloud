package com.ynu.yuyue.cloud.bookservice.service.impl;

import com.ynu.yuyue.cloud.bookservice.domain.Book;
import com.ynu.yuyue.cloud.bookservice.service.BookService;
import org.springframework.stereotype.Service;

/**
 * Created by yuyue on 2017/9/7.
 */
@Service
public class BookServiceImpl implements BookService {
    @Override
    public Book getOneBook(Integer id) {
        Book b=new Book();
        b.setBookId(id);
        b.setBookName("书"+id);
        return b;
    }
}
