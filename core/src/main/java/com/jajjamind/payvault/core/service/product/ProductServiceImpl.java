package com.jajjamind.payvault.core.service.product;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.product.models.NameAndNoteModel;
import com.jajjamind.payvault.core.api.product.models.Product;
import com.jajjamind.payvault.core.api.product.models.ProductCategory;
import com.jajjamind.payvault.core.exception.ServiceApiNotSupported;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.enums.AccountStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;
import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import com.jajjamind.payvault.core.jpa.models.product.TProductCategory;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.repository.account.AccountRepository;
import com.jajjamind.payvault.core.repository.product.ProductRepository;
import com.jajjamind.payvault.core.repository.product.TProductCategoryRepository;
import com.jajjamind.payvault.core.service.utilities.AccountUtilities;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Autowired
    public TProductCategoryRepository productCategoryRepository;

    @Override
    public Product add(Product product) {
        throw new ServiceApiNotSupported("Product addition is not supported");
    }

    @Override
    public Product get(Long id) {

        final TProduct product = findByIdNotNull(id);

        final Product mProduct = new Product();

        BeanUtilsCustom.copyProperties(product,mProduct);
        mProduct.setStatus(Boolean.TRUE.equals(product.getNonActive()) ? StatusEnum.NOT_ACTIVE : StatusEnum.ACTIVE);

        final NameAndNoteModel category = new NameAndNoteModel();
        BeanUtilsCustom.copyProperties(product.getProductCategory(),category);
        mProduct.setProductCategory(category);

        final NameAndNoteModel provider = new NameAndNoteModel();
        BeanUtilsCustom.copyProperties(product.getProvider(),provider);
        mProduct.setProvider(provider);

        final NameAndNoteModel rootProvider = new NameAndNoteModel();
        BeanUtilsCustom.copyProperties(product.getRootProvider(),rootProvider);
        mProduct.setRootProvider(rootProvider);

        if(mProduct.getProductAccount() != null ) {
            final Account acc = new Account();
            BeanUtilsCustom.copyProperties(product.getProductAccount(), product);
            mProduct.setProductAccount(acc);
        }
        return mProduct;
    }

    @Transactional
    @Override
    public Product update(Product product) {

        product.validate();
        Validate.notNull(product.getId(),"Product ID to update cannot be null");

        TProduct savedProduct = productRepository.findProductWithAccountNullable(product.getId()).orElseThrow(() -> new BadRequestException(ErrorMessageConstants.PRODUCT_WITH_ID_NOT_FOUND,product.getId()));

        savedProduct.setOfficialName(product.getOfficialName());
        savedProduct.setHasCharge(product.getHasCharge());
        savedProduct.setHasTariff(product.getHasTariff());
        savedProduct.setHasSmsNotification(product.getHasSmsNotification());

        TAccount oldAccount = savedProduct.getProductAccount();

        if((oldAccount != null && product.getProductAccount() != null) || (oldAccount == null && product.getProductAccount() != null)){

            if(oldAccount == null){
                TAccount account = accountRepository.findById(product.getProductAccount().getId())
                        .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,product.getProductAccount().getId()));

                AccountUtilities.checkThatAccountCanBeAssigned(account);

                account.setAssigned(Boolean.TRUE);
                account.setAccountStatus(AccountStatusEnum.ACTIVE);

                auditService.stampAuditedEntity(account);
                accountRepository.save(account);

                savedProduct.setProductAccount(account);
            }else if (!product.getProductAccount().getId().equals(oldAccount.getId())){

                AccountUtilities.checkThatAccountCanBeUnAssigned(oldAccount);
                TAccount account = accountRepository.findById(product.getProductAccount().getId())
                        .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,product.getProductAccount().getId()));

                AccountUtilities.checkThatAccountCanBeAssigned(account);

                oldAccount.setAccountStatus(AccountStatusEnum.CLOSED);
                oldAccount.setAssigned(Boolean.FALSE);
                oldAccount.setClosedOn(DateTimeUtil.getCurrentUTCTime());

                TUser user = new TUser();
                user.setId(auditService.getLoggedInUser().getId());

                oldAccount.setClosedBy(user);
                oldAccount.setStatusDescription(AccountStatusEnum.CLOSED.getDescription());

                auditService.stampAuditedEntity(oldAccount);
                accountRepository.save(oldAccount);

                account.setStatusDescription(AccountStatusEnum.ACTIVE.getDescription());
                account.setAccountStatus(AccountStatusEnum.ACTIVE);
                account.setAssigned(Boolean.TRUE);

                auditService.stampAuditedEntity(account);
                accountRepository.save(account);


            }

        }

         auditService.stampAuditedEntity(savedProduct);
         productRepository.save(savedProduct);

        return product;
    }

    @Override
    public Product delete(Long id) {
        throw new ServiceApiNotSupported("Product deletion is not supported");
    }

    @Override
    public void deactivateProduct(Long id) {
        Optional<TProduct> oProduct = productRepository.findById(id);

        Validate.isTrue(oProduct.isPresent(),ErrorMessageConstants.PRODUCT_WITH_ID_NOT_FOUND,id);
        TProduct product = oProduct.get();
        Validate.isTrue(!product.getNonActive(),"Product is already de-activated");

        product.setNonActive(Boolean.TRUE);

        auditService.stampAuditedEntity(product);
        productRepository.save(product);


    }

    @Override
    public void activateProduct(Long id) {
        TProduct product = productRepository.findByIdWithCategory(id).orElseThrow(() -> new BadRequestException(ErrorMessageConstants.PRODUCT_WITH_ID_NOT_FOUND,id));
        Validate.isTrue(product.getNonActive(),"Product is already activated");

        TProductCategory category = productCategoryRepository.findById(
                product.getProductCategory().getId()).orElseThrow(() -> new BadRequestException("Failed to retrieve product Category"));

        category.getProducts().forEach(t -> {
            if(t.getId().equals(id)){
                Validate.isTrue(t.getNonActive(),"There is already an active product with the same root provider");
            }
        });

        product.setNonActive(Boolean.FALSE);
        auditService.stampAuditedEntity(product);

        productRepository.save(product);

    }

    @Override
    public List<Product> getAll() {
        Iterable<TProduct> product = productRepository.findAll();
        List<Product> products = new ArrayList<>();

        product.forEach(t -> {

            Product tm = new Product();
            products.add(tm);
        });

        return products;
    }


    @Override
    public List<ProductCategory> getWithCategoryGrouping() {

        Iterable<TProductCategory> categories = productCategoryRepository.findAll();
        List<ProductCategory> category = new ArrayList<>();

        categories.forEach(t ->
        {
            List<Product> product = new ArrayList<>();
            t.getProducts().forEach(k -> product.add(getProductFromTProduct(k)));

            ProductCategory c = new ProductCategory();
            c.setId(t.getId());
            c.setName(t.getName());
            c.setProducts(product);

            category.add(c);
        });

        return category;
    }


    private Product getProductFromTProduct(TProduct t){
        final Product tm = new Product();
        BeanUtilsCustom.copyProperties(t,tm);
        tm.setStatus(Boolean.TRUE.equals(t.getNonActive()) ? StatusEnum.NOT_ACTIVE : StatusEnum.ACTIVE);

        return tm;

    }

    private TProduct findByIdNotNull(Long id){
       return  productRepository.findByIdWithAllParams(id).orElseThrow(() -> new BadRequestException(ErrorMessageConstants.PRODUCT_WITH_ID_NOT_FOUND,id));
    }
}
