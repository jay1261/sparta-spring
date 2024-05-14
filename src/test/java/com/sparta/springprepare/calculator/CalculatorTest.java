package com.sparta.springprepare.calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    @DisplayName("더하기 테스트")
    public void operate(){
        Calculator calculator = new Calculator();
        Double result = calculator.operate(10, "+", 20);

        Assertions.assertThat((double)30).isEqualTo(result);
//        org.junit.jupiter.api.Assertions.assertEquals(30,result);
    }

    @Test
    @DisplayName("나누기 테스트")
    public void test2(){
        Calculator calculator = new Calculator();
        Double result = calculator.operate(8, "/", 2);

        Assertions.assertThat((double) 4).isEqualTo(result);
    }
}