package com.fisher.securityapp.dao;

import com.fisher.securityapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonDao extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
}
