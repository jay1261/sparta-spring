package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {
    private Long id;
    private String userName;
    private String contents;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.userName = memo.getUserName();
        this.contents = memo.getContents();
    }
}
