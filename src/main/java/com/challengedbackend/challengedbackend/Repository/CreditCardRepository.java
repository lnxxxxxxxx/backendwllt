package com.challengedbackend.challengedbackend.Repository;

import com.challengedbackend.challengedbackend.Model.Account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
