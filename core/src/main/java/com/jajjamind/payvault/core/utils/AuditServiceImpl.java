package com.jajjamind.payvault.core.utils;

import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.BaseEntityInteger;
import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author akena
 * 13/01/2021
 * 17:38
 **/
@Service
public class AuditServiceImpl implements AuditService{

    @Override
    public void stampIntegerEntity(BaseEntityInteger entity) {
        Date date = DateTimeUtil.getCurrentUTCTime();
       if(entity.getId()== null){
           entity.setCreatedOn(date);
       }

       entity.setModifiedOn(DateTimeUtil.getCurrentUTCTime());
    }

    @Override
    public void stampLongEntity(BaseEntityLong entity) {
        Date date = DateTimeUtil.getCurrentUTCTime();
        if(entity.getId()== null){
            entity.setCreatedOn(date);
        }

        entity.setModifiedOn(DateTimeUtil.getCurrentUTCTime());
    }

    @Override
    public void stampAuditedEntity(AuditedEntity auditedEntity) {

        LoggedInUser user = getLoggedInUser();
        Validate.notNull(user,"Only a logged in user can make this change");
        Date date = DateTimeUtil.getCurrentUTCTime();
        TUser tUser = new TUser();
        tUser.setId(user.getId());

        if(auditedEntity.getId() == null){
            auditedEntity.setCreatedOn(date);
            auditedEntity.setCreatedBy(tUser);
        }

        auditedEntity.setModifiedBy(tUser);
        auditedEntity.setModifiedOn(date);
    }

    @Override
    public LoggedInUser getLoggedInUser() {
        final OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()){
            Map<String,?> decoded = (LinkedHashMap)( (OAuth2AuthenticationDetails)authentication.getDetails()).getDecodedDetails();
            LoggedInUser user = new LoggedInUser();
            user.setUsername((String)decoded.get("username"));
            user.setId(Long.valueOf((Integer) decoded.get("id")));
            return user;
        }

        return null;
    }
}
