package com.sparta.spartascheduler.entitiy;

import com.sparta.spartascheduler.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 500)
    private String contents;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.id = scheduleRequestDto.getId();
        this.title = scheduleRequestDto.getTitle();
        this.contents = scheduleRequestDto.getContents();
        this.userName = scheduleRequestDto.getUserName();
        this.password = scheduleRequestDto.getPassword();
    }

    public void update(ScheduleRequestDto scheduleRequestDto) {
        this.title = scheduleRequestDto.getTitle();
        this.contents = scheduleRequestDto.getContents();
        this.userName = scheduleRequestDto.getUserName();
    }
}
