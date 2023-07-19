package pl.piomin.services.quarkus.employee.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pl.piomin.services.quarkus.employee.model.Employee;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

	public List<Employee> findByDepartment(Long departmentId){
		return find("departmentId", departmentId).list();
	}

	public List<Employee> findByOrganization(Long organizationId){
		return find("organizationId", organizationId).list();
	}

}
