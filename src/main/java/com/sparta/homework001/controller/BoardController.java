package com.sparta.homework001.controller;

import com.sparta.homework001.model.Board;
import com.sparta.homework001.model.Reply;
import com.sparta.homework001.repository.BoardRepository;
import com.sparta.homework001.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    //게시글 조회
    @GetMapping("/")
    public String listBoards(Model model) {
        List<Board> listBoards = boardRepository.findAllByOrderByModifiedAtDesc();
        model.addAttribute("boardList", listBoards);
        return "list";
    }
    //게시글 쓰기
    @GetMapping("/write")
    public String writeBoard() {

        return "write";
    }
    //게시글 상세조회
    @GetMapping("/detail/{id}")
    public String detailBoard(@PathVariable Long id, Model model) {
        Board detailBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );

        List<Reply> replyList = replyRepository.findByBoardId(id);

        model.addAttribute("boardDetail", detailBoard);
        model.addAttribute("replyList", replyList);
        return "detail";

    }



}
