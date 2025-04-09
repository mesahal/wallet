package com.wallet.userservice.presenter.rest.api;

import com.wallet.userservice.common.utils.AppUtils;
import com.wallet.userservice.domain.entity.Customer;
import com.wallet.userservice.repository.CustomerRepository;
import com.wallet.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppUtils.BASE_URL)
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public String signIn(@RequestBody Customer customer) {
        return authService.verify(customer);
    }

    @PostMapping("/signup")
    public Customer signUp(@RequestBody Customer customer) {
        return authService.registerCustomer(customer);
    }
}
