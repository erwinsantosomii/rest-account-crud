package com.mii.poc.mcsvc.dao;

import com.mii.poc.mcsvc.domain.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ErwinSn
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Account findByAccountNumber(String accountNumber);
    
    @Query("select a from Account a " +
          "where lower(a.accountName) like lower(concat('%', :searchTerm, '%')) "
            + "or a.accountNumber like concat('%', :searchTerm, '%') "
            + "or a.cif like concat('%', :searchTerm, '%')")
    List<Account> search(@Param("searchTerm") String searchTerm);
    
}
