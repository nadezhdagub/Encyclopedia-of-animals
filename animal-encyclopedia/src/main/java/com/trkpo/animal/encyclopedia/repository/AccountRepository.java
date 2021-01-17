
package com.trkpo.animal.encyclopedia.repository;

import com.trkpo.animal.encyclopedia.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByEmail(String email);
    Optional<Account> findById(UUID id);

}
