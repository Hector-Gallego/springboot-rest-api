package dev.hectorgallego.springbootrestapi.service.Role;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hectorgallego.springbootrestapi.model.Role;
import dev.hectorgallego.springbootrestapi.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role crateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        getRoleById(id);
        roleRepository.deleteById(id);  
    }

    @Override
    @Transactional(readOnly=true)
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no se encontro al rol con id:"+ id));
    }
    
}
