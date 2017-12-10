package uutiset.wepauutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.wepauutiset.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByName(String name);
}
