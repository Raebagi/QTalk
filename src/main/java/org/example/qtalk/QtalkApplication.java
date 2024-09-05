package org.example.qtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class QtalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(QtalkApplication.class, args);
        System.out.println("hello");
    //test

}
