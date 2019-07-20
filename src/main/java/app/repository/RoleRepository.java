package app.repository;

import app.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {

    Role findRoleByName(String name);
}
