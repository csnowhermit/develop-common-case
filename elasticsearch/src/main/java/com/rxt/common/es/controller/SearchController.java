/**
 * FileName: SearchController
 * Author:   Ren Xiaotian
 * Date:     2018/12/3 21:17
 */

package com.rxt.common.es.controller;

import com.rxt.common.es.service.BookService;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {
    @Autowired
    private BookService bookService;

    @GetMapping("/get/book/novel")
    @ResponseBody
    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id) {
        GetResponse response = bookService.getById(id);

        if (!response.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response.getSource(), HttpStatus.OK);
    }

    @PostMapping("add/book/novel")
    @ResponseBody
    public ResponseEntity add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author,
            @RequestParam(name = "word_count") int wordCount,
            @RequestParam(name = "publish_date") String publishDate
    ) {
        IndexResponse response;
        try {
            response = bookService.add(title, author, wordCount, publishDate);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("remove/book/novel")
    public ResponseEntity remove(@RequestParam(name = "id") String id) {
        DeleteResponse response = bookService.remove(id);

        return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
    }

    @PutMapping("modify/book/novel")
    @ResponseBody
    public ResponseEntity modify(@RequestParam(name = "id") String id,
                                 @RequestParam(name = "title", required = false) String title,
                                 @RequestParam(name = "author", required = false) String author) {
        UpdateResponse response;
        try {
            response = bookService.modify(id, title, author);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
    }

}
