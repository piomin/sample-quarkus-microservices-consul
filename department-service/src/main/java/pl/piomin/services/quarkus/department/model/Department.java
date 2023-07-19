package pl.piomin.services.quarkus.department.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long organizationId;
    @NotBlank
    private String name;
    @Transient
    private List<Employee> employees = new ArrayList<>();

}
