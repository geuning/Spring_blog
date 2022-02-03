package com.sparta.homework001.controller;

import com.sparta.homework001.model.Board;
import com.sparta.homework001.repository.BoardRepository;
import com.sparta.homework001.dto.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiBoardController {

    private final BoardRepository boardRepository;
    // private final BoardService boardService;
//    @ResponseBody

    //게시글 작성
    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return  boardRepository.save(board);
    }
    //게시글 조회
    @GetMapping("/api/boards")
    public List<Board> getBoards() {

        return boardRepository.findAllByOrderByModifiedAtDesc();
    }
    //게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;   //???????????????????????????????????????????????????????????????????????????????
    }

//    @GetMapping("/api/boards/{id}")
//    public Long detailBoard(@PathVariable Long id){
//        boardRepository.findAllById(id);
//        return id
//    }
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ



}
