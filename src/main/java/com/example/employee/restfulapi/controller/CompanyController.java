package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    //获取company列表
    @GetMapping
    public List<Company> employeeList() {
        return companyRepository.findAll();
    }

    //获取某个具体company
    @GetMapping(value = "/{id}")
    public Company getCompany(@PathVariable(value = "id") Long id) {
        return companyRepository.findOne(id);
    }

    //获取某个具体company下所有employee列表
    @GetMapping(value = "/{id}/employees")
    public List getEmployee(@PathVariable(value = "id") Long id) {
        return companyRepository.findOne(id).getEmployees();
    }

    //companies/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
    @GetMapping(value = "/page/{pageNo}/pageSize/{pageSize}")
    public Page<Company> getCompanyPage(@PathVariable(value = "pageNo") int pageNo, @PathVariable(value = "pageSize") int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        return companyRepository.findAll(pageable);
    }

}
