package Svider.Zadanie2.repository;

import Svider.Zadanie2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findAllByOrderByLastNameAsc();

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);
}
