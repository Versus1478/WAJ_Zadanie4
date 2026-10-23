package Svider.Zadanie2.service;

import Svider.Zadanie2.entity.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> getSortedEmployees();
}