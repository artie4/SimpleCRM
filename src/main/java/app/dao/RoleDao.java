package app.dao;

import app.entity.Role;

public interface RoleDao {

    Role findRoleByName(String roleName);
}
