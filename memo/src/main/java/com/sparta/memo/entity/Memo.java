package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "memo")
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name="contents", nullable = false, length = 500)
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
