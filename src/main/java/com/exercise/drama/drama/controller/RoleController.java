package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.Genre;
import com.exercise.drama.drama.model.Role;
import com.exercise.drama.drama.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> getAll(){ return roleRepository.findAll(); }
    @GetMapping("{roleId}")
    public Role getByRoleId(@PathVariable Long roleId){
        return roleRepository.getOne(roleId);
    }
    @PostMapping
    public Role save(@RequestBody Role role){
        return roleRepository.save(role);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Role update(@RequestBody Role role){
        Role oldRole = roleRepository.getOne(role.getRoleId());
        BeanUtils.copyProperties(role, oldRole, "roleId");
        return roleRepository.saveAndFlush(oldRole);
    }
    @RequestMapping(value = "{roleId}", method = RequestMethod.DELETE)
    public void deleteByRoleId(@PathVariable Long roleId){
        roleRepository.deleteById(roleId);
    }
}
