package pl.piomin.services.quarkus.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
