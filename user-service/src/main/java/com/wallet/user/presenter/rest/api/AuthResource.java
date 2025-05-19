package com.wallet.user.presenter.rest.api;

import com.wallet.user.common.utils.AppUtils;
import com.wallet.user.domain.entity.Customer;
import com.wallet.user.service.AuthService;
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
