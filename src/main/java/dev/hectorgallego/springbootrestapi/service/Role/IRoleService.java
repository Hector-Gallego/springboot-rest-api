package dev.hectorgallego.springbootrestapi.service.Role;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.Role;

public interface IRoleService {

    List<Role> getAllRoles();
    Role crateRole(Role role);
    void deleteRoleById(Long id);
    
}
