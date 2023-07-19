package pl.piomin.services.quarkus.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long organizationId;
    @NotNull
    private Long departmentId;
    @NotBlank
    private String name;
    @Min(1)
    @Max(100)
    private int age;
    @NotBlank
    private String position;

}
