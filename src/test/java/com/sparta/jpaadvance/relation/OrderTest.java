package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.entity.Food;
import com.sparta.jpaadvance.entity.Order;
import com.sparta.jpaadvance.entity.User;
import com.sparta.jpaadvance.repository.FoodRepository;
import com.sparta.jpaadvance.repository.OrderRepository;
import com.sparta.jpaadvance.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class OrderTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("중간 테이블 Order Entity 테스트")
    void test1() {

        User user = new User();
        user.setName("Robbie2");

        Food food = new Food();
        food.setName("후라이드 치킨2");
        food.setPrice(15000);

        // 주문 저장
        Order order = new Order();
        order.setUser(user); // 외래 키(연관 관계) 설정
        order.setFood(food); // 외래 키(연관 관계) 설정

        userRepository.save(user);
        foodRepository.save(food);
        orderRepository.save(order);
    }

    @Test
    @DisplayName("중간 테이블 Order Entity 조회")
    void test2() {
        // 1번 주문 조회
        Order order = orderRepository.findById(1L).orElseThrow(NullPointerException::new);

        // order 객체를 사용하여 고객 정보 조회
        User user = order.getUser();
        System.out.println("user.getName() = " + user.getName());
        List<Order> orderList = user.getOrderList();
        for (Order order1 : orderList) {
            System.out.println(order1.getId());
            System.out.println(order1.getUser().getName());
            System.out.println(order1.getFood().getName());
        }

        // order 객체를 사용하여 음식 정보 조회
        Food food = order.getFood();
        System.out.println("food.getName() = " + food.getName());
        System.out.println("food.getPrice() = " + food.getPrice());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("기존 유저 불러와서 order 추가해보기")
    void test3(){
        User user = userRepository.findById(1L).orElseThrow();

        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getName() = " + user.getName());

        Food food = foodRepository.findById(1L).orElseThrow();

        Order order = new Order();
        order.setUser(user);
        order.setFood(food);

        orderRepository.save(order);
    }
}