package com.sparta.spartascheduler.repository;

import com.sparta.spartascheduler.dto.ScheduleResponseDto;
import com.sparta.spartascheduler.entitiy.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByCreatedAtDesc();
}
