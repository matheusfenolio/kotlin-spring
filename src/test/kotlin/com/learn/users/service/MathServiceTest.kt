package com.learn.users.service

import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathServiceTest {
    private lateinit var mathService: MathService

    @BeforeAll
    fun init() {
        mathService = MathService()
    }

    @Test
    @DisplayName("Should add numbers")
    fun testAddition(){

        val result =  mathService.addition(1F, 2F)
        Assertions.assertEquals(3F, result);
    }

    @Test
    @DisplayName("Should subtract numbers")
    fun testSubtraction(){
        val result =  mathService.subtraction(2F, 1F)
        Assertions.assertEquals(1F, result);
    }

    @Test
    @DisplayName("Should multiply numbers")
    fun testMultiplication(){

        val result =  mathService.multiplication(2F, 5F)
        Assertions.assertEquals(10F, result);
    }

    @Test
    @DisplayName("Should divide numbers")
    fun testDivision(){
        val result =  mathService.division(10F, 2F)
        Assertions.assertEquals(5F, result);
    }
}