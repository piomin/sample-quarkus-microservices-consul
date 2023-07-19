package pl.piomin.services.quarkus.department.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pl.piomin.services.quarkus.department.model.Department;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<Department> {

	public List<Department> findByOrganization(Long organizationId){
		return find("organizationId", organizationId).list();
	}

}
