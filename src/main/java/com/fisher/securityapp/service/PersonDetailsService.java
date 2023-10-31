package com.fisher.securityapp.service;

import com.fisher.securityapp.dao.PersonDao;
import com.fisher.securityapp.model.Person;
import com.fisher.securityapp.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {
    private final PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personDao.findByUsername(username);
        return new PersonDetails(person
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
