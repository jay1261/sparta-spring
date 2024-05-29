package com.sparta.spartascheduler.controller;

import com.sparta.spartascheduler.dto.ScheduleRequestDto;
import com.sparta.spartascheduler.dto.ScheduleResponseDto;
import com.sparta.spartascheduler.entitiy.Schedule;
import com.sparta.spartascheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    @GetMapping("/schedule")
    public ScheduleResponseDto getSchedule(@RequestParam Long id){
        return scheduleService.getSchedule(id);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(){
        return scheduleService.getSchedules();
    }

    @PutMapping("/schedule")
    public ScheduleResponseDto updateSchedule(@RequestParam Long id, @RequestBody ScheduleRequestDto scheduleRequestDto){
        return scheduleService.updateSchedule(id, scheduleRequestDto);
    }

    @DeleteMapping("/schedule")
    public Long deleteSchedule(@RequestParam Long id, @RequestBody Map<String,String> map){
        return scheduleService.deleteSchedule(id, map.getOrDefault("password", ""));
    }
}
