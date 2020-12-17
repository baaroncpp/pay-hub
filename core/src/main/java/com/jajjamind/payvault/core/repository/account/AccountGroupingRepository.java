package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.jpa.models.account.TAccountGrouping;
import org.springframework.data.repository.CrudRepository;

public interface AccountGroupingRepository extends CrudRepository<Long, TAccountGrouping> {
}
