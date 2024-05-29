package com.sparta.entity;

import jakarta.persistence.*;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Table(name = "memo") // 매핑할 테이블의 이름을 지정
public class Memo {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    // length: 컬럼 길이 지정
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}