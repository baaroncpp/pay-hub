package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommission;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

/**
 * @author akena
 * 08/02/2021
 * 11:52
 **/
public class ProductCommissionRepositoryImpl implements ProductCommissionRepositoryCustom{

    @PersistenceContext
    public EntityManager em;


    @Override
    public Optional<TProductCommission> findProductCommissionGeneric(Long productId) {

        final Query q = em.createQuery("SELECT u from TProductCommission u inner join fetch u.product where u.product.nonActive = false AND u.status = 'ACTIVE'", TProductCommission.class);
        q.setFirstResult(0);
        q.setMaxResults(1);
        return  Optional.of((TProductCommission) q.getSingleResult());
    }
}
