package com.payhub.data.airtel.service;

import com.payhub.data.airtel.entity.Bundle;

public interface BundleService {
	Bundle addBundle(Bundle bundle);
	Bundle updateBundle(Bundle bundle);
	Bundle removeBundle(Bundle bundle);
	Iterable<Bundle> getAllBundles();
	Bundle getBundleById(String id);
	Bundle getBundleByName(String name);
}
