package ru.kata.spring.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("select r FROM Role r", Role.class).getResultList();
    }
}
