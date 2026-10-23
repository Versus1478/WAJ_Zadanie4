package Svider.Zadanie2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Meno je povinné")
    @Size(min = 2, max = 50, message = "Meno musí mať 2-50 znakov")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Priezvisko je povinné")
    @Size(min = 2, max = 50, message = "Priezvisko musí mať 2-50 znakov")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull(message = "Dátum narodenia je povinný")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Email je povinný")
    @Email(message = "Neplatný email")
    @Pattern(
            // Pravidlá podľa RFC 5321/5322:
            //  - lokálna časť max. 64 znakov, bez bodky na začiatku/konci a bez dvoch bodiek za sebou
            //  - doména: labely 1-63 znakov, nezačínajú ani nekončia pomlčkou, oddelené bodkou
            //  - TLD aspoň 2 písmená (zachytí aj "meno@domena" bez TLD, ktoré @Email prepustí)
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_%+-]+(?:\\.[A-Za-z0-9_%+-]+)*"
                    + "@(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+[A-Za-z]{2,63}$",
            message = "Email nemá platný formát (napr. meno@domena.sk)"
    )
    @Size(max = 100, message = "Email môže mať najviac 100 znakov")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Pattern(
            regexp = "^\\+421\\d{9}$",
            message = "Telefónne číslo musí začínať +421 a obsahovať presne 9 číslic za ním."
    )
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Pracovná pozícia je povinná")
    @Size(min = 2, max = 100, message = "Pracovná pozícia musí mať 2-100 znakov")
    @Column(name = "job_title", nullable = false, length = 100)
    private String jobTitle;

    @NotNull(message = "Plat je povinný")
    @Positive(message = "Plat musí byť kladné číslo")
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "full_time")
    private boolean fullTime;

    public Employee() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public boolean isFullTime() { return fullTime; }
    public void setFullTime(boolean fullTime) { this.fullTime = fullTime; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", firstName='" + firstName + "', lastName='" + lastName + "'}";
    }
}
