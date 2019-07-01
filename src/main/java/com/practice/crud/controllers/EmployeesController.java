package com.practice.crud.controllers;


import com.practice.crud.models.Employee;
import com.practice.crud.repositories.EmployeeRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EmployeesController {


    @Autowired
    EmployeeRepository employeeRepository;


    //@RequestMapping(value = "/employees", method = RequestMethod.GET, produces="application/json")
    @GetMapping("/employees")
    public List<Employee> allCountry() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        System.out.println(employee);
        return employeeRepository.save(employee);
    }


    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") int id,
                           @Valid @RequestBody Employee employeeDetails) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());

        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id" + id));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }


}
