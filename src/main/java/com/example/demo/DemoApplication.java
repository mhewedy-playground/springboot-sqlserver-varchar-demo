package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    public DemoApplication(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Company company = new Company();
        company.name = "شركة الشركة";
        companyRepository.save(company);

        companyRepository.findByName(company.name).forEach(System.out::println);
    }
}
