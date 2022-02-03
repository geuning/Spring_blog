package com.sparta.homework001.model;

import com.sparta.homework001.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
@SequenceGenerator(
        name = "REPLY_SEQ_GENERATOR",
        sequenceName = "REPLY_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Reply extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy=GenerationType.AUTO, generator="REPLY_SEQ_GENERATOR")
    @Id
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private String contents;

//    public Reply(Long id, String contents) {
//        this.id = id;
//        this.contents = contents;
//    }

    public Reply(String contents, Board board) {
        this.board = board;
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void updateContents(String contents) {
        //빵틀에서 나온 실제 빵의 값을 바꿔줘야 하니까 this로
        //우리가 변경할 값을 실어나르는 Dto의 값이 우리가 findById로 찾은 이 Memo안에 쏙 들어가게됨
        this.contents = contents;
    }
}
