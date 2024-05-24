package com.example.boardproejct.controller;

import com.example.boardproejct.domain.Board;
import com.example.boardproejct.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 페이징 처리된 게시글 목록 보기
    @GetMapping("/boards")
    public String boardList(Model model,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Iterable<Board> boards = boardService.findAllBoard(pageable);
        model.addAttribute("boards", boards);
        model.addAttribute("currentPage", page);
        return "boards/list";
    }

    // 게시글 1개 보기
    @GetMapping("/view")
    public String fineBoard(Model model,
                            @RequestParam Long id) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boards/detailBoard";
    }
}
