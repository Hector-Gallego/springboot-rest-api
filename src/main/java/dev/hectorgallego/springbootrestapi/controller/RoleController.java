package dev.hectorgallego.springbootrestapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hectorgallego.springbootrestapi.model.Role;
import dev.hectorgallego.springbootrestapi.service.Role.IRoleService;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final IRoleService roleService;
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getAllRoles();
    }

    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role){
        return roleService.crateRole(role);
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRoleById(id);
    }
    
}
