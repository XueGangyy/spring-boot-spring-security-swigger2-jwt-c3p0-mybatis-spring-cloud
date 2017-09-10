package com.ynu.yuyue.cloud.bookservice.controller;

import com.ynu.yuyue.cloud.bookservice.domain.Book;
import com.ynu.yuyue.cloud.bookservice.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuyue on 2017/9/7.
 */
@RestController
@RequestMapping("/book")
@PreAuthorize("hasRole('USER')")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation(value="获取测试书籍的信息")
    @GetMapping("/getOneBook/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Integer id){
      return new ResponseEntity<Book>(bookService.getOneBook(id), HttpStatus.ACCEPTED);
  }
}
