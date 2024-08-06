package com.ohgiraffers.example.board.repository;

import com.ohgiraffers.example.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
