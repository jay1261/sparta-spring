package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import com.sparta.springauth.food.Pizza;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {

    @Autowired
    @Qualifier("chicken") // 빈이 여러개라서 타입을 지정을 헤줘야한다.
    Food food;

    @Autowired
    Food chicken; // 직접 이름을 바꿔줄 수 있다.

    @Autowired
    Pizza pizza; // 아예 타입을 Pizza로 해도 된다.

    @Test
    @DisplayName("같은 타입의 빈 ")
    void test1(){
        food.eat();

        chicken.eat();
        pizza.eat();
    }
}
