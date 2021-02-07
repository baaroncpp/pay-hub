package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akena
 * 17/12/2020
 * 13:08
 **/
@Repository
public interface ProductRepository extends CrudRepository<TProduct,Long> {

    @Query("SELECT u from TProduct u WHERE u.name = :name and u.provider = :provider")
    Optional<TProduct> findByNameAndProvider(@Param("name") String name,@Param("provider") String provider);

    @Query("Select u from TProduct u inner join fetch u.productAccount WHERE u.id = :id")
    Optional<TProduct> findProductWithAccount(@Param("id") Long id);

}
