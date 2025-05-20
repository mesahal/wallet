package com.wallet.transaction.service.Impl;

import com.wallet.transaction.common.exceptions.ResourceNotFoundException;
import com.wallet.transaction.domain.common.ApiResponse;
import com.wallet.transaction.domain.entity.Account;
import com.wallet.transaction.domain.enums.ResponseMessage;
import com.wallet.transaction.domain.response.BalanceResponse;
import com.wallet.transaction.repository.AccountRepository;
import com.wallet.transaction.service.BalanceService;
import com.wallet.transaction.service.BaseService;
import com.wallet.transaction.service.LocaleMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public class BalanceServiceImpl extends BaseService implements BalanceService{

    private final AccountRepository accountRepository;

    public BalanceServiceImpl(AccountRepository accountRepository, LocaleMessageService localeMessageService) {
        super(localeMessageService);
        this.accountRepository = accountRepository;
    }

    @Override
    public ApiResponse<BalanceResponse> showBalance(String username) {

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.ACCOUNT_NOT_FOUND));

        return new ApiResponse<>(ResponseMessage.OPERATION_SUCCESSFUL.getCode(), getMessage(ResponseMessage.OPERATION_SUCCESSFUL), BalanceResponse.builder()
                .balance(account.getBalance())
                .build());
    }
}
