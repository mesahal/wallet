package com.wallet.transaction.service.Impl;

import com.wallet.transaction.domain.common.ApiResponse;
import com.wallet.transaction.domain.entity.Account;
import com.wallet.transaction.domain.response.BalanceResponse;
import com.wallet.transaction.repository.AccountRepository;
import com.wallet.transaction.service.BalanceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final AccountRepository accountRepository;

    public BalanceServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ApiResponse<BalanceResponse> showBalance(String username) {

        Optional<Account> account = accountRepository.findByUsername(username);
        BalanceResponse balanceResponse = new BalanceResponse();
        if (account.isPresent()) {
            balanceResponse.setBalance(account.get().getBalance());
        }
        else {
            return new ApiResponse<>("404", "Account not found", balanceResponse);
        }
        return new ApiResponse<>("200","OK",balanceResponse);
    }
}
