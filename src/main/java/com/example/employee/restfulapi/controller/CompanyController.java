package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Company> employeeList() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Company getCompany(@PathVariable(value = "id") Long id) {
        return companyRepository.findOne(id);
    }

}
