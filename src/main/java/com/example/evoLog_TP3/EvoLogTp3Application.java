package com.example.evoLog_TP3;

import com.example.evoLog_TP3.model.Product;
import com.example.evoLog_TP3.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.example.evoLog_TP3")
public class EvoLogTp3Application {


	public static void main(String[] args) {
		SpringApplication.run(EvoLogTp3Application.class, args);

	}
}





