package com.sparta.spartascheduler.repository;

import com.sparta.spartascheduler.dto.ScheduleResponseDto;
import com.sparta.spartascheduler.entitiy.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByCreatedAtDesc();

    Optional<Schedule> findByIdIsAndPassword(Long id, String password);
}
