package pl.piomin.services.quarkus.department.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
