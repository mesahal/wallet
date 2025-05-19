package com.wallet.transaction.service.Impl;

import com.wallet.transaction.domain.common.ApiResponse;
import com.wallet.transaction.domain.entity.Account;
import com.wallet.transaction.domain.entity.TransactionHistory;
import com.wallet.transaction.domain.enums.TransactionType;
import com.wallet.transaction.domain.request.AddMoneyRequest;
import com.wallet.transaction.domain.request.TransferMoneyRequest;
import com.wallet.transaction.domain.response.TransactionHistoryResponse;
import com.wallet.transaction.domain.response.TransactionResponse;
import com.wallet.transaction.repository.AccountRepository;
import com.wallet.transaction.repository.TransactionHistoryRepository;
import com.wallet.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    @Override
    public ApiResponse<TransactionResponse> addMoney(AddMoneyRequest request) {

        Optional<Account> account = accountRepository.findByUsername(request.getUsername());
        if(account.isPresent()){
            account.get().setBalance(account.get().getBalance().add(request.getAmount()));
            accountRepository.save(account.get());
        }
        else {
            Account newAccount = new Account();
            newAccount.setUsername(request.getUsername());
            newAccount.setBalance(request.getAmount());
            accountRepository.save(newAccount);
        }

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setReceiverUsername(request.getUsername());
        transactionHistory.setAmount(request.getAmount());
        transactionHistory.setType(TransactionType.ADD_MONEY.getCode());
        transactionHistory.setCreatedAt(LocalDateTime.now());
        transactionHistory.setSenderUsername("SYSTEM");
        transactionHistory.setTransactionId(getTransactionId());
        transactionHistoryRepository.save(transactionHistory);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(transactionHistory.getTransactionId());
        transactionResponse.setAmount(request.getAmount());
        transactionResponse.setReceiverAccount(request.getUsername());
        transactionResponse.setSenderAccount("SYSTEM");
        return new ApiResponse<>("200","Added money successfully",transactionResponse);
    }

    @Transactional
    @Override
    public ApiResponse<TransactionResponse> transferMoney(TransferMoneyRequest request) {
        Optional<Account> senderAccount = accountRepository.findByUsername(request.getSenderUsername());
        Optional<Account> receiverAccount = accountRepository.findByUsername(request.getReceiverUsername());

        if(senderAccount.isPresent()){
            senderAccount.get().setBalance(senderAccount.get().getBalance().subtract(request.getAmount()));
            accountRepository.save(senderAccount.get());
        }
        else {
            return new ApiResponse<>("404","Sender account not found",null);
        }
        if(receiverAccount.isPresent()){
            receiverAccount.get().setBalance(receiverAccount.get().getBalance().add(request.getAmount()));
            accountRepository.save(receiverAccount.get());
        }
        else {
            Account newAccount = new Account();
            newAccount.setUsername(request.getSenderUsername());
            newAccount.setBalance(request.getAmount());
            accountRepository.save(newAccount);
        }

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setReceiverUsername(request.getReceiverUsername());
        transactionHistory.setAmount(request.getAmount());
        transactionHistory.setType(TransactionType.TRANSFER_MONEY.getCode());
        transactionHistory.setCreatedAt(LocalDateTime.now());
        transactionHistory.setSenderUsername(request.getSenderUsername());
        transactionHistory.setTransactionId(getTransactionId());
        transactionHistoryRepository.save(transactionHistory);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(transactionHistory.getTransactionId());
        transactionResponse.setAmount(request.getAmount());
        transactionResponse.setReceiverAccount(request.getReceiverUsername());
        transactionResponse.setSenderAccount(request.getSenderUsername());
        return new ApiResponse<>("200","Transferred money successfully",transactionResponse);
    }

    @Override
    public List<TransactionHistoryResponse> transactionHistory(String username) {
        List<TransactionHistory> transactionHistoryList = transactionHistoryRepository.findAllByReceiverUsernameOrSenderUsername(username, username);

        return transactionHistoryList.stream()
                .map(this::toTransactionHistoryResponse)
                .collect(Collectors.toList());
    }

    private String getTransactionId(){
        return "TXN-" + RandomStringUtils.randomAlphanumeric(12).toUpperCase();
    }
    
    private TransactionHistoryResponse toTransactionHistoryResponse(TransactionHistory transactionHistory){
        return TransactionHistoryResponse.builder()
                .transactionId(transactionHistory.getTransactionId())
                .amount(transactionHistory.getAmount())
                .type(TransactionType.getTransactionType(transactionHistory.getType()))
                .createdAt(transactionHistory.getCreatedAt())
                .senderUsername(transactionHistory.getSenderUsername())
                .receiverUsername(transactionHistory.getReceiverUsername())
                .build();
    }
}







