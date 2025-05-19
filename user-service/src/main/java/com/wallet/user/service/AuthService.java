package com.wallet.user.service;

import com.wallet.user.domain.entity.Customer;
import com.wallet.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Customer registerCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public String verify(Customer customer) {
        Authentication authentication =  authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(customer.getUsername());
        }
        return "failure";
    }
}
