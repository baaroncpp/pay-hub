package com.payhub.data.airtel.service.imp;

import com.payhub.data.airtel.entity.Bundle;
import com.payhub.data.airtel.repository.BundleRepository;
import com.payhub.data.airtel.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.com.jajjamind.commons.utils.UUIDUtil;
import java.util.Optional;

@Service
public class BundleServiceImp implements BundleService {
	
	private BundleRepository bundleRepository;
	
	@Autowired
	public BundleServiceImp(BundleRepository bundleRepository) {
		this.bundleRepository = bundleRepository;
	}

	@Override
	public Bundle addBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		Optional<Bundle> bund = bundleRepository.findByName(bundle.getName());
		
		if(bund.isPresent()) {
			throw new RuntimeException("bundle of name: "+bundle.getName()+" already exist");
		}
		
		bundle.setId(UUIDUtil.getUUID());
		return bundleRepository.save(bundle);
	}

	@Override
	public Bundle updateBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		Optional<Bundle> bund = bundleRepository.findById(bundle.getId());
	
		if(!bund.isPresent()) {
			throw new RuntimeException("bundle of id: "+bundle.getId()+" does not exist");
		}
		
		return bundleRepository.save(bundle);
	}

	@Override
	public Bundle removeBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		Optional<Bundle> bund = bundleRepository.findById(bundle.getId());
		
		if(!bund.isPresent()) {
			throw new RuntimeException("bundle of id: "+bundle.getId()+" does not exist");
		}
		 
		bundleRepository.delete(bundle);
		
		return bundle;
	}

	@Override
	public Iterable<Bundle> getAllBundles() {
		// TODO Auto-generated method stub
		return bundleRepository.findAll();
	}

	@Override
	public Bundle getBundleById(String id) {
		// TODO Auto-generated method stub
		Optional<Bundle> bundle = bundleRepository.findById(id);
		
		if(!bundle.isPresent()) {
			throw new RuntimeException("bundle of id: "+id+" does not exist");
		}
		return bundle.get();
	}

	@Override
	public Bundle getBundleByName(String name) {
		// TODO Auto-generated method stub
		Optional<Bundle> bundle = bundleRepository.findByName(name);
		
		if(!bundle.isPresent()) {
			throw new RuntimeException("bundle of name: "+name+" does not exist");
		}
		return bundle.get();
	}

}
