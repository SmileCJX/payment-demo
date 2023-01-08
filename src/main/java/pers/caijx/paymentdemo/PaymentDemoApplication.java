package pers.caijx.paymentdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class PaymentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentDemoApplication.class, args);
    }

}
