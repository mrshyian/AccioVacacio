package com.codecool.travelhelper.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRoleTable, Long> {
    UserRoleTable findByName(String name);
}
