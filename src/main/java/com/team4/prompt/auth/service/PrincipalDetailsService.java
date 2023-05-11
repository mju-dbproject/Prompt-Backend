package com.team4.prompt.auth.service;

import com.team4.prompt.auth.model.PrincipalDetails;
import com.team4.prompt.employee.model.Employee;
import com.team4.prompt.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final EmployeeService employeeService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeService.findByUserId(username);
        return new PrincipalDetails(employee);
    }
}
