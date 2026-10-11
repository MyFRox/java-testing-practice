package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();
        System.out.println("--- 每个测试前 ---");
    }

    @AfterEach
    void tearDown(){
        System.out.println("--- 每个测试后 ---");
        calculator = null;
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("=== 所有测试开始前，只跑一次 ===");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("=== 所有测试结束后，只跑一次 ===");
    }





    @DisplayName("多参数加法测试-methodsource")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("addData")
    void add2(int a,int b ,int expect) {
        assertEquals(expect,calculator.add(a,b));
    }

    static Stream<Arguments> addData(){
        return Stream.of(
                Arguments.of(2, 2, 4),
                Arguments.of(-1, 1, 0),
                Arguments.of(-2, -3, -5),
                Arguments.of(50, 50, 100),
                Arguments.of(Integer.MAX_VALUE, 0, Integer.MAX_VALUE)
        );
    }


    @DisplayName("单参数测试是否为正数")
    @ParameterizedTest(name = "{0}是否是正数:{1}")
    @MethodSource("testIsPositive")
    void isPositive(int a,boolean expecet){
        assertEquals(expecet,a>0);
    }

    static Stream<Arguments> testIsPositive(){
        return Stream.of(
                Arguments.of(100,true),
                Arguments.of(-1, false),
                Arguments.of(0, false)
        );
    }

    @DisplayName("单参数测试文本是否为空")
    @ParameterizedTest(name = "\"{0}\":是否为空字符：{1}")
    @MethodSource("testStringIsEmpty")
    void isBlank(String s,boolean expect){
        assertEquals(expect,s.isEmpty());
    }

    static Stream<Arguments> testStringIsEmpty(){
        return Stream.of(
                Arguments.of("",true),
                Arguments.of("ddd",false)
        );
    }



    @DisplayName("除3测试")
    @ParameterizedTest(name = "[{index}] {0}/3 ={1}")
    @MethodSource("provideData")
    void divideByThree(int number,int expect){
        assertEquals(expect,calculator.divide(number,3));
    }

    static Stream<Arguments> provideData(){
        return Stream.of(
                Arguments.of(6, 2),
                Arguments.of(9, 3),
                Arguments.of(12, 4)
        );
    }

    @Test
    void divideByZeroShouldThrow() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(1, 0)
        );
        assertEquals("除数不能为 0", ex.getMessage());
    }
}