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

    @Query("SELECT u from TProduct u WHERE u.productCategory = :category and u.rootProvider = :rootProvider and u.nonActive = false and u.id <> :id")
    Optional<TProduct> findNotMatchingIdByCategoryAndActive(@Param("category") String category,@Param("rootProvider") String rootProvider,@Param("id") Long id);

    @Query("SELECT u from TProduct u left join fetch u.productAccount WHERE u.productCode = :productCode")
    Optional<TProduct> findProductByCode(@Param("productCode") String productCode);

    @Query("SELECT u from TProduct u inner join fetch u.productAccount WHERE " +
            "u.productCategory = :category and u.rootProvider = :rootProvider and u.nonActive = false " +
            "and u.productAccount.accountStatus = 'ACTIVE' AND u.productAccount.assigned = true")
    Optional<TProduct> findByCategoryDetailsForTransaction(@Param("category") String category,@Param("rootProvider") String rootProvider);

}
