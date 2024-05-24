package com.example.boardproejct.service;

import com.example.boardproejct.domain.Board;
import com.example.boardproejct.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 목록 보기
    public Page<Board> findAllBoard(Pageable pageable) {
        Pageable boards = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));
        return boardRepository.findAll(boards);
    }

    // 게시글 1개 조회
    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 사용자가 없습니다."));
    }

    // 게시글 등록
    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }
}
