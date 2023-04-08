package eu.stelmaszak.fullstackapp;

import eu.stelmaszak.fullstackapp.demo.DemoController;
import eu.stelmaszak.fullstackapp.demo.DemoService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
class SpringBootAppTests {

    private final DemoService service;
    private final DemoController controller;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
        assertThat(controller).isNotNull();
    }

}
