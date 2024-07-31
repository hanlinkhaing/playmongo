package com.handev.playmongo.services;

import com.handev.playmongo.models.Comment;
import com.handev.playmongo.repos.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll(Pageable.ofSize(10).withPage(0)).getContent();
    }
}
