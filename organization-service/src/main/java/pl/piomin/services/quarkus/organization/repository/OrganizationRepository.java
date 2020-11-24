package pl.piomin.services.quarkus.organization.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pl.piomin.services.quarkus.organization.model.Organization;

@ApplicationScoped
public class OrganizationRepository implements PanacheRepository<Organization> {

}
