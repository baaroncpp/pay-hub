package com.jajjamind.payvault.core.service.product;

import com.jajjamind.commons.text.StringUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.product.models.Product;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.enums.AccountStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.ProductCategoryEnum;
import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import com.jajjamind.payvault.core.repository.account.AccountRepository;
import com.jajjamind.payvault.core.repository.product.ProductRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 18/01/2021
 * 03:23
 **/
@Service
public class ProductServiceImpl  implements ProductService{

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public AuditService auditService;

    @Autowired
    public ProductRepository productRepository;

    @Override
    public Product add(Product product) {
        product.validate();

        var accountId = product.getProductAccount().getId();
        Validate.notNull(accountId, ErrorMessageConstants.ACCOUNT_ID_CANNOT_BE_NULL);

        Optional<TAccount> account = accountRepository.findById(accountId);

        Validate.isTrue(account.isPresent(),ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,accountId);
        Validate.isTrue(account.get().getAccountStatus().equals(AccountStatusEnum.NOT_ACTIVE),"Account with ID %s is not usable ",account.get().getId());
        Validate.isTrue(!account.get().getAssigned(), ErrorMessageConstants.ACCOUNT_ALREADY_ASSIGNED);

        TProduct tProduct = new TProduct();
        BeanUtils.copyProperties(product,tProduct);

        tProduct.setProductAccount(account.get());

        auditService.stampAuditedEntity(tProduct);

        productRepository.save(tProduct);

        Optional<TProduct> oSavedProduct = productRepository.findByNameAndProvider(product.getName(),product.getProvider());

        Validate.isTrue(oSavedProduct.isPresent(),"Product creation failed");
        TProduct savedProduct = oSavedProduct.get();

        final String productCode = getProductCode(product.getProductCategory(),savedProduct.getId());
        savedProduct.setProductCode(productCode);

        productRepository.save(savedProduct);

        return product;
    }

    @Override
    public Product get(Long id) {

        Optional<TProduct> oProduct = productRepository.findById(id);
        Validate.isTrue(oProduct.isPresent(),ErrorMessageConstants.PRODUCT_WITH_ID_NOT_FOUND,id);

        TProduct product = oProduct.get();
        Product mProduct = new Product();

        BeanUtils.copyProperties(product,mProduct);

        return mProduct;
    }

    @Override
    public Product update(Product product) {

        product.validate();
        Validate.notNull(product.getId(),"Product ID to update cannot be null");

        Optional<TProduct> oProduct = productRepository.findById(product.getId());

        Validate.isTrue(oProduct.isPresent(),ErrorMessageConstants.PRODUCT_WITH_ID_NOT_FOUND,product.getId());

        TProduct savedProduct = oProduct.get();

        var changingAccount = false;
        var newAccountId = product.getProductAccount().getId();

        TAccount oldAccount = savedProduct.getProductAccount();

        if(!newAccountId.equals(oldAccount.getId()))
        {
            Validate.isTrue(oldAccount.getAvailableBalance().compareTo(BigDecimal.ZERO) == 0,"Current attached account should have zero balance before it can be changed");

            Optional<TAccount> account = accountRepository.findById(product.getProductAccount().getId());

            Validate.isTrue(account.isPresent(),ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,product.getProductAccount().getId());
            Validate.isTrue(account.get().getAccountStatus().equals(AccountStatusEnum.NOT_ACTIVE),"Account with ID %s is not usable ",account.get().getId());
            Validate.isTrue(!account.get().getAssigned(), ErrorMessageConstants.ACCOUNT_ALREADY_ASSIGNED);

            changingAccount = true;
        }

        BeanUtils.copyProperties(product,savedProduct);

        final TAccount k = new TAccount();
        k.setId(product.getProductAccount().getId());
        savedProduct.setProductAccount(k);

        auditService.stampAuditedEntity(savedProduct);
        productRepository.save(savedProduct);

        if(changingAccount){

            Optional<TAccount> account = accountRepository.findById(product.getProductAccount().getId());
            if(account.isPresent()){
                TAccount newAccount = account.get();
                newAccount.setAssigned(Boolean.TRUE);
                newAccount.setAccountStatus(AccountStatusEnum.ACTIVE);

                auditService.stampAuditedEntity(newAccount);
                accountRepository.save(newAccount);
            }

            oldAccount.setAccountStatus(AccountStatusEnum.NOT_ACTIVE);
            oldAccount.setAssigned(Boolean.FALSE);

            auditService.stampAuditedEntity(oldAccount);
            accountRepository.save(oldAccount);
        }

        return product;
    }

    @Override
    public Product delete(Long id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        throw new UnsupportedOperationException();
    }

    private String getProductCode(ProductCategoryEnum categoryEnum,Long id){
        final String code = StringUtil.getSequenceForDefinedZeros(String.valueOf(id),4);
        return categoryEnum.getCode().concat(code);
    }
}
