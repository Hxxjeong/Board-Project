package com.example.boardproejct.controller;

import com.example.boardproejct.domain.Board;
import com.example.boardproejct.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 페이징 처리된 게시글 목록 보기
    @GetMapping("/list")
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

    // 게시글 등록 폼
    @GetMapping("/writeform")
    public String writeForm(Model model) {
        model.addAttribute("board", new Board());
        return "boards/create";
    }

    // 게시글 등록
    @PostMapping("/writeform")
    public String createBoard(Model model, Board board) {
        Board b = boardService.createBoard(board);
        model.addAttribute("board", b);
        return "redirect:/list";
    }

    // 게시글 삭제 form
    @GetMapping("/deleteform")
    public String deleteForm(@RequestParam Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boards/delete";
    }

    // 게시글 삭제
    @PostMapping("/boards/delete")
    public String deleteBoard(@ModelAttribute Board board, RedirectAttributes redirectAttributes) {
        try {
            boardService.deleteBoardById(board.getId(), board.getPassword());
        }
        catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/deleteform?id=" + board.getId();
        }
        return "redirect:/list";
    }

    // 게시글 수정 form
    @GetMapping("/updateform")
    public String updateForm(@RequestParam Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boards/update";
    }

    // 게시글 수정
    @PostMapping("/boards/update")
    public String updateBoard(@ModelAttribute Board board, RedirectAttributes redirectAttributes) {
        try {
            boardService.updateBoard(board.getId(), board, board.getPassword());
        }
        catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/updateform?id=" + board.getId();
        }
        return "redirect:/list";
    }
}
