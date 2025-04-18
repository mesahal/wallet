package com.wallet.userservice.presenter.rest.api;


import com.wallet.userservice.common.utils.AppUtils;
import com.wallet.userservice.domain.entity.Customer;
import com.wallet.userservice.repository.CustomerRepository;
import com.wallet.userservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppUtils.BASE_URL)
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public String addMoney(HttpServletRequest request) {
        return "success " + request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/users")
    public List<Customer>  getUsers() {
        return customerRepository.findAll();
    }
}
