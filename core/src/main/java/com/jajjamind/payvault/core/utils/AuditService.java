package com.jajjamind.payvault.core.utils;

/**
 * @author akena
 * 13/01/2021
 * 17:36
 **/

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.BaseEntityInteger;
import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;
import com.jajjamind.payvault.core.security.models.LoggedInUser;

public interface  AuditService {

   void stampIntegerEntity(BaseEntityInteger entity);
   void stampLongEntity(BaseEntityLong entity);
   void stampAuditedEntity(AuditedEntity auditedEntity);
   LoggedInUser getLoggedInUser();
}
