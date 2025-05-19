package com.wallet.user.service.impl;
import com.wallet.user.domain.entity.Customer;
import com.wallet.user.domain.entity.UserPrincipal;
import com.wallet.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements  UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);

        if(customer == null){
            throw new UsernameNotFoundException(username + " not found");
        }

        return new UserPrincipal(customer);
    }
}
