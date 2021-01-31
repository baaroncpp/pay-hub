package com.jajjamind.payvault.core.service.user;

import com.jajjamind.payvault.core.api.agent.models.Country;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author akena
 * 29/01/2021
 * 13:55
 **/
@Service
public class UserMetaServiceImpl {

    public UserMeta getUserMetaFromJpa(TUserMeta userMeta){
        if(userMeta != null) {
            final UserMeta meta = new UserMeta();
            BeanUtils.copyProperties(userMeta, meta);

            final TCountry c = userMeta.getCountryCode();
            if (c != null) {
                final Country country = new Country();
                BeanUtils.copyProperties(c, country);
                meta.setCountryCode(country);
            }

            return meta;
        }

        return null;

    }

    public TUserMeta updateUserMetaProperties(UserMeta userMeta, TUserMeta oldUserMeta){
        TUserMeta updatedMeta = new TUserMeta();
        BeanUtils.copyProperties(userMeta,updatedMeta);

        updatedMeta.setUserId(oldUserMeta.getUserId());
        updatedMeta.setAgentId(oldUserMeta.getAgentId());
        updatedMeta.setCreatedBy(oldUserMeta.getCreatedBy());
        updatedMeta.setCreatedOn(oldUserMeta.getCreatedOn());

        return updatedMeta;
    }
}
