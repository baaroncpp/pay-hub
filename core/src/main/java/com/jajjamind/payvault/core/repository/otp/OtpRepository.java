package com.jajjamind.payvault.core.repository.otp;

import com.jajjamind.payvault.core.jpa.models.otp.TOtp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 17/12/2020
 * 13:07
 **/
@Repository
public interface OtpRepository extends CrudRepository<TOtp,Long> {
}
