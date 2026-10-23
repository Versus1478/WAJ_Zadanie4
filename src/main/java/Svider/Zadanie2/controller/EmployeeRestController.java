package Svider.Zadanie2.controller;

import Svider.Zadanie2.entity.Employee;
import Svider.Zadanie2.response.ResponseFactory;
import Svider.Zadanie2.response.ResponseWrapper;
import Svider.Zadanie2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final ResponseFactory response;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ResponseFactory response) {
        this.employeeService = employeeService;
        this.response = response;
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseWrapper<List<Employee>>> getAllEmployees() {
        return response.ok("Zamestnanci úspešne načítaní", employeeService.findAll());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        return response.ok("Zamestnanec úspešne načítaný", employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<ResponseWrapper<Employee>> createEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(null);
        Employee savedEmployee = employeeService.save(employee);
        return response.created("Zamestnanec úspešne vytvorený", savedEmployee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> updateEmployee(
            @PathVariable Integer id, @Valid @RequestBody Employee employee) {

        employeeService.findById(id);

        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return response.ok("Zamestnanec úspešne aktualizovaný", updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return response.ok("Zamestnanec úspešne zmazaný");
    }

    @GetMapping("/employees/search")
    public ResponseEntity<ResponseWrapper<List<Employee>>> searchEmployees(
            @RequestParam String firstName, @RequestParam String lastName) {
        List<Employee> employees = employeeService.findByFirstNameAndLastName(firstName, lastName);
        return response.ok("Výsledky vyhľadávania", employees);
    }

    @GetMapping("/employees/sorted")
    public ResponseEntity<ResponseWrapper<List<Employee>>> getSortedEmployees() {
        return response.ok("Zamestnanci zoradení podľa priezviska", employeeService.getSortedEmployees());
    }
}
