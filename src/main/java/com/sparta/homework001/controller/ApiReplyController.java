package com.sparta.homework001.controller;

import com.sparta.homework001.dto.ReplyRequestDto;
import com.sparta.homework001.model.Board;
import com.sparta.homework001.model.Reply;
import com.sparta.homework001.repository.BoardRepository;
import com.sparta.homework001.repository.ReplyRepository;
import com.sparta.homework001.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiReplyController {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final ReplyService replyService;
//    @ResponseBody

    @PostMapping("/boards/{id}/reply")
    public Reply createReply(@PathVariable Long id, @RequestBody ReplyRequestDto replyRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        Reply reply = new Reply(replyRequestDto.getContents(), board); //board ????????????????????????????????????
        return replyRepository.save(reply);
    }

    //댓글 작성
//    @PostMapping("/boards/{id}/reply")
//    public CreateReplyResponseDto createReply(@PathVariable Long id, @RequestBody ReplyRequestDto replyRequestDto) {
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
//        );
//        Reply reply = new Reply(replyRequestDto, board);
//        Reply savedReply = replyRepository.save(reply);
//
//        CreateReplyResponseDto createReplyResponseDto = new CreateReplyResponseDto();
//        createReplyResponseDto.setContents(savedReply.getContents());
//        createReplyResponseDto.setReplyId(savedReply.getId());
//
//        return createReplyResponseDto;
//    }

//    //댓글 조회
//    @GetMapping("/api/boards/{id}/reply")
//    public List<Reply> getReply(@PathVariable Long id) {
//        List<Reply> replyList = replyRepository.findByBoardId(id);
//        return replyList;
//    }

    //댓글 삭제
    @DeleteMapping("/reply/{replyId}")
    public Long deleteReply(@PathVariable Long replyId) {
        replyRepository.deleteById(replyId);
        return replyId;  // ??????????????????????????????????????????????????????????????????????????????????/
    }

    @PutMapping("/reply/{replyId}")
    public Long updateReply(@PathVariable Long replyId,@RequestBody ReplyRequestDto replyRequestDto){
        return replyService.updateReply(replyId, replyRequestDto);

    }


//    @GetMapping("/api/boards/{id}")
//    public Long detailBoard(@PathVariable Long id){
//        boardRepository.findAllById(id);
//        return id
//    }
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ



}
