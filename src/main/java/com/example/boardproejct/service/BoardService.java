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
                .orElseThrow(() -> new NoSuchElementException("해당하는 게시글이 없습니다."));
    }

    // 게시글 등록
    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoardById(Long id, String password) {
        Board board = boardRepository.findById(id).orElse(null);

        // 비밀번호가 일치하면 삭제
        if(board.getPassword().equals(password)) {
            boardRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    // 게시글 수정
    public void updateBoard(Long id, Board updatedBoard, String password) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 게시글이 없습니다."));

        // 비밀번호 일치 여부 확인
        if(!board.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 기존 게시글 업데이트
        board.setName(updatedBoard.getName());
        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());

        boardRepository.save(board);
    }
}
