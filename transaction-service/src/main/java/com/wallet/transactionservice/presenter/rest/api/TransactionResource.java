package com.wallet.transactionservice.presenter.rest.api;

import com.wallet.transactionservice.common.AppUtils;
import com.wallet.transactionservice.domain.common.ApiResponse;
import com.wallet.transactionservice.domain.request.AddMoneyRequest;
import com.wallet.transactionservice.domain.request.TransferMoneyRequest;
import com.wallet.transactionservice.domain.response.TransactionResponse;
import com.wallet.transactionservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppUtils.BASE_URL)
public class TransactionResource {

    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add-money")
    public ResponseEntity<ApiResponse<TransactionResponse>> addMoney(@RequestBody AddMoneyRequest request) {
        ApiResponse<TransactionResponse> response = transactionService.addMoney(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer-money")
    public ResponseEntity<ApiResponse<TransactionResponse>> transferMoney(@RequestBody TransferMoneyRequest request) {
        ApiResponse<TransactionResponse> response = transactionService.transferMoney(request);
        return ResponseEntity.ok(response);
    }
}
