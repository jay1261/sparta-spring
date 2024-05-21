package com.sparta.springauth.food;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 같은 타입의 빈이 여러개이면 Primary가 있는 타입이 우선권을 갖는다.
public class Chicken implements Food{

    @Override
    public void eat() {
        System.out.println("치킨을 먹습니다.");
    }
}
