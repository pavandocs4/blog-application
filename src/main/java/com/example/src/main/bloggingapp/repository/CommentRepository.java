package com.example.src.main.bloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.src.main.bloggingapp.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
