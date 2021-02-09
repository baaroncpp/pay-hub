package com.payhub.data.mtn.service.imp;

import com.payhub.data.mtn.constants.BundleCategory;
import com.payhub.data.mtn.entity.jpa.MtnBundle;
import com.payhub.data.mtn.models.MtnBundleModel;
import com.payhub.data.mtn.repository.MtnBundleRepository;
import com.payhub.data.mtn.service.MtnBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jajjamind.commons.utils.UUIDUtil;
import java.util.List;
import java.util.Optional;

@Service
public class MtnBundleServiceImp implements MtnBundleService {

    private MtnBundleRepository mtnBundleRepository;

    @Autowired
    public MtnBundleServiceImp(MtnBundleRepository mtnBundleRepository){
        this.mtnBundleRepository = mtnBundleRepository;
    }

    @Override
    public MtnBundle addMtnBundle(MtnBundleModel mtnBundleModel) {
        Optional<MtnBundle> mtnBundle = mtnBundleRepository.findByBundleName(mtnBundleModel.getMtnBundleId());
        if(mtnBundle.isPresent()){
            throw new RuntimeException("Bundle "+mtnBundleModel.getMtnBundleId()+" already exists");
        }

        MtnBundle mtnBundle1 = new MtnBundle();
        mtnBundle1.setId(UUIDUtil.getUUID());
        mtnBundle1.setBundleName(mtnBundleModel.getMtnBundleId());
        mtnBundle1.setCategory(mtnBundleModel.getCategory());
        mtnBundle1.setDescription(mtnBundleModel.getDescription());

        return mtnBundleRepository.save(mtnBundle1);
    }

    @Override
    public MtnBundle getMtnBundleBy(String id) {
        Optional<MtnBundle> mtnBundle = mtnBundleRepository.findById(id);
        if(mtnBundle.isEmpty()){
            throw new RuntimeException("Bundle with "+id+" does not exists");
        }
        return mtnBundle.get();
    }

    @Override
    public MtnBundle getMtnBundleByMtnBundleId(String mtnBundleId) {
        Optional<MtnBundle> mtnBundle = mtnBundleRepository.findByBundleName(mtnBundleId);
        if(mtnBundle.isEmpty()){
            throw new RuntimeException("Bundle "+mtnBundleId+" does not exist exists");
        }
        return mtnBundle.get();
    }

    @Override
    public List<MtnBundle> getAllBundleByCategory(BundleCategory bundleCategory) {
        return mtnBundleRepository.findAllByBundleCategory(bundleCategory);
    }

    @Override
    public List<MtnBundle> getAllMtnBundles() {
        return mtnBundleRepository.findAll();
    }
}
