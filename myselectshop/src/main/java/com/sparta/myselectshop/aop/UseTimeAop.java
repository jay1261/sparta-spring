package com.sparta.myselectshop.aop;

import com.sparta.myselectshop.entity.ApiUseTime;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.ApiUseTimeRepository;
import com.sparta.myselectshop.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j(topic = "UseTimeAop")
@Aspect
@Component
@RequiredArgsConstructor
public class UseTimeAop {

    private final ApiUseTimeRepository apiUseTimeRepository;

    @Pointcut("execution(* com.sparta.myselectshop.controller.ProductController.*(..))")
    private void product(){}

    @Pointcut("execution(* com.sparta.myselectshop.controller.FolderController.*(..))")
    private void folder(){}

    @Pointcut("execution(* com.sparta.myselectshop.naver.controller.NaverApiController.*(..))")
    private void naver(){}


    @Around("product() || folder() || naver()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            // 핵심기능 수행
            Object object = joinPoint.proceed();
            return object;
        } finally {
            long endTime = System.currentTimeMillis();

            long runTime = endTime - startTime;

            // 로그인 회원이 없는 경우에는 수행시간을 기록하지 않음
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null && auth.getPrincipal().getClass() == UserDetailsImpl.class){
                UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
                User loginUser = userDetails.getUser();

                ApiUseTime apiUseTime = apiUseTimeRepository.findByUser(loginUser).orElse(null);
                if(apiUseTime == null) {
                    // 로그인 회원의 기록이 없으면 새로 등록
                    apiUseTime = new ApiUseTime(loginUser, runTime);
                } else {
                    // 기록이 이미 있으면 업데이트
                    apiUseTime.addUseTime(runTime);
                }
                log.info("[API Use Time] Username: " + loginUser.getUsername() + ", totalTime: " + apiUseTime.getTotalTime());
                apiUseTimeRepository.save(apiUseTime);
            }

        }
    }
}
