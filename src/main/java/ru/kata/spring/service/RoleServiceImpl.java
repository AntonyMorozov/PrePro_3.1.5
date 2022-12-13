package ru.kata.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.dao.RoleDao;
import ru.kata.spring.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
