package com.wallet.transaction.repository;

import com.wallet.transaction.domain.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findAllByReceiverUsernameOrSenderUsername(String receiverUsername, String senderUsername);
}
