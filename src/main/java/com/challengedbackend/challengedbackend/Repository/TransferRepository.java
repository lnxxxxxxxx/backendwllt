package com.challengedbackend.challengedbackend.Repository;

import com.challengedbackend.challengedbackend.Model.Transfer.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {
}
