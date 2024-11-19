package com.vag.product;

import org.springframework.boot.SpringApplication;

public class TestSpringProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
