package com.api.tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ProdutosApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(ProdutosApplication.class, args);

    }

}
