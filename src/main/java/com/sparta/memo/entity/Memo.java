package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String userName;
    private String contents;

    public Memo(MemoRequestDto memoRequestDto) {
        this.userName = memoRequestDto.getUserName();
        this.contents = memoRequestDto.getContents();
    }

    public void update(MemoRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.contents = requestDto.getContents();
    }
}
