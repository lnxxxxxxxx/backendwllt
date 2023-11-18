package com.challengedbackend.challengedbackend.Repository;

import com.challengedbackend.challengedbackend.Model.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAlias(String alias);

    Optional<Account> findByCvu(String cvu);

}
