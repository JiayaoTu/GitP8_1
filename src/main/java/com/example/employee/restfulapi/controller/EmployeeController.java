package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value="/employees")
@RestController
public class EmployeeController {
    //在此处完成Employee API
    @Autowired
    EmployeeRepository employeeRepository;
    //获取employee列表
    @GetMapping
    List<Employee> findEmployees()
    {
        return employeeRepository.findAll();
    }

    //获取某个具体employee
    @GetMapping(value="/{id}")
    Employee getEmployee(@PathVariable(value = "id") Long id)
    {
        return employeeRepository.findOne(id);
    }

    //分页查询，page等于1，pageSize等于5
    @GetMapping(value = "/page/{pageNo}/pageSize/{pageSize}")
    public Page<Employee> getEmployeePage(@PathVariable(value = "pageNo") int pageNo, @PathVariable(value = "pageSize") int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        return employeeRepository.findAll(pageable);
    }

    //筛选出所有男性Employee
    @GetMapping(value = "/male")
    public List<Employee> getMaleEmployeeList() {
        return employeeRepository.findAll((Specification<Employee>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(cb.equal(root.get("gender").as(String.class), "male"));

            Predicate[] p = new Predicate[list.size()];
            query.where(cb.and(list.toArray(p)));
            return query.getRestriction();
        });
    }
}
