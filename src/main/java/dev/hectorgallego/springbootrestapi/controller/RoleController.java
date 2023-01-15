package dev.hectorgallego.springbootrestapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hectorgallego.springbootrestapi.model.Role;
import dev.hectorgallego.springbootrestapi.service.Role.IRoleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final IRoleService roleService;
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(),HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id){
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<Object> createRole(@Valid @RequestBody Role role){
        return new ResponseEntity<>(roleService.crateRole(role), HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){

        roleService.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    
}
