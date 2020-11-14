package com.example.awsbook.controller.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloResponseDtoTest {

    @Test
    public void LombokTest() throws Exception {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dtd = new HelloResponseDto(name, amount);

        // then
        assertThat(dtd.getName()).isEqualTo(name);
        assertThat(dtd.getAmount()).isEqualTo(amount);

    }
}