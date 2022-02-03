package com.sparta.homework001.service;

import com.sparta.homework001.dto.ReplyRequestDto;
import com.sparta.homework001.model.Reply;
import com.sparta.homework001.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository; //MemoService의 멤버변수로 memoRepository선언
    //꼭 필요하다고 얘기를 해줘야해서 final 써줘야함
    //그리고 final로 선언된 녀석이 있으면 그거 생성할때 무조건 같이 넣어줄게? 라는 의미의
    // @RequiredArgsConstructor추가해주고 또 스프링한테
    //얘가 서비스인것도 알려줘야해서 @Service도 추가

    @Transactional                                //업데이트 할떄 얘가 DB에 진짜 반영이 되어야해 라는 의미

    public Long updateReply(Long replyId, ReplyRequestDto replyrequestDto) {
        //update메소드에서 재료는 id와 변경시킬때 필요한 정보를 물고다니는 Dto

        // 1. 업데이트할라면 우선 찾아야함 찾을라면 repository가 있어야함 그래서 15번줄에 선언해줌
        // orElseThrow 아이디없을때 오류를 발생시키라고 서버한테 말해주는것
        // 그뒤 결과물을 Memo클래스의 memo라는 녀석이 반환됨
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        // 2. 반환된 memo 그값자체를 업데이트 하는 과정에서 requestDto를 가지고 생각해야함
        //어떤 녀석이 업데이트 됬는지 아이디 반환 해줘야함
        // update는 MemoClass에 만들어져 있어야함
        reply.updateContents(replyrequestDto.getContents());
        return reply.getId();  //????????????????????????왜 getID???????
    }

    public ReplyRepository getReplyRepository() {
        return replyRepository;
    }
}
