package com.handev.playmongo.controllers;

import com.handev.playmongo.dto.Response;
import com.handev.playmongo.models.Comment;
import com.handev.playmongo.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Response<List<Comment>>> getComments() {
        return Response.<List<Comment>>builder()
                .status(HttpStatus.OK)
                .message("Comments retrieved successfully")
                .data(commentService.getAllComments())
                .build();
    }
}
