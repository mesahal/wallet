package com.wallet.transaction.service.Impl;

import com.wallet.transaction.common.exceptions.ResourceNotFoundException;
import com.wallet.transaction.domain.common.ApiResponse;
import com.wallet.transaction.domain.entity.Account;
import com.wallet.transaction.domain.entity.TransactionHistory;
import com.wallet.transaction.domain.enums.ResponseMessage;
import com.wallet.transaction.domain.mapper.TransactionHistoryMapper;
import com.wallet.transaction.domain.request.AddMoneyRequest;
import com.wallet.transaction.domain.request.TransferMoneyRequest;
import com.wallet.transaction.domain.response.TransactionHistoryResponse;
import com.wallet.transaction.domain.response.TransactionResponse;
import com.wallet.transaction.repository.AccountRepository;
import com.wallet.transaction.repository.TransactionHistoryRepository;
import com.wallet.transaction.service.BaseService;
import com.wallet.transaction.service.LocaleMessageService;
import com.wallet.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final TransactionHistoryMapper transactionHistoryMapper;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionHistoryRepository transactionHistoryRepository, TransactionHistoryMapper transactionHistoryMapper, LocaleMessageService localeMessageService) {
        super(localeMessageService);
        this.accountRepository = accountRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.transactionHistoryMapper = transactionHistoryMapper;
    }

    @Transactional
    @Override
    public ApiResponse<TransactionResponse> addMoney(AddMoneyRequest request) {

        Account account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.RECEIVER_ACCOUNT_NOT_FOUND));

        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        TransactionHistory transactionHistory = transactionHistoryMapper.buildTransactionHistoryForAddMoney(request);
        transactionHistoryRepository.save(transactionHistory);

        return new ApiResponse<>(ResponseMessage.OPERATION_SUCCESSFUL.getCode(), getMessage(ResponseMessage.OPERATION_SUCCESSFUL.getMessageKey()), TransactionResponse.builder()
                .transactionId(transactionHistory.getTransactionId())
                .amount(request.getAmount())
                .receiverAccount(request.getUsername())
                .senderAccount("SYSTEM")
                .build());
    }

    @Transactional
    @Override
    public ApiResponse<TransactionResponse> transferMoney(TransferMoneyRequest request) {
        Account senderAccount = accountRepository.findByUsername(request.getSenderUsername())
                .orElseThrow(()-> new ResourceNotFoundException(ResponseMessage.SENDER_ACCOUNT_NOT_FOUND));

        senderAccount.setBalance(senderAccount.getBalance().subtract(request.getAmount()));
        accountRepository.save(senderAccount);

        Account receiverAccount = accountRepository.findByUsername(request.getReceiverUsername())
                .orElseThrow(()-> new ResourceNotFoundException(ResponseMessage.RECEIVER_ACCOUNT_NOT_FOUND))  ;

        receiverAccount.setBalance(receiverAccount.getBalance().add(request.getAmount()));
        accountRepository.save(receiverAccount);

        TransactionHistory transactionHistory = transactionHistoryMapper.buildTransactionHistoryForTransferMoney(request);
        transactionHistoryRepository.save(transactionHistory);

        return new ApiResponse<>(ResponseMessage.OPERATION_SUCCESSFUL.getCode(), getMessage(ResponseMessage.OPERATION_SUCCESSFUL.getMessageKey()), TransactionResponse.builder()
                .transactionId(transactionHistory.getTransactionId())
                .amount(request.getAmount())
                .receiverAccount(request.getReceiverUsername())
                .senderAccount(request.getSenderUsername())
                .build());
    }

    @Override
    public ApiResponse<List<TransactionHistoryResponse>> transactionHistory(String username) {
        List<TransactionHistory> transactionHistoryList = transactionHistoryRepository.findAllByReceiverUsernameOrSenderUsername(username, username);

        return new ApiResponse<>(ResponseMessage.OPERATION_SUCCESSFUL.getCode(), getMessage(ResponseMessage.OPERATION_SUCCESSFUL.getMessageKey()),transactionHistoryList.stream()
                .map(transactionHistoryMapper::toTransactionHistoryResponse)
                .collect(Collectors.toList()));
    }
}







