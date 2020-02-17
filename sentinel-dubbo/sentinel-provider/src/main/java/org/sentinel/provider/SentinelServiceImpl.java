package org.sentinel.provider;

import org.apache.dubbo.config.annotation.Service;
import org.sentinel.api.SentinelService;

@Service
public class SentinelServiceImpl implements SentinelService{

	@Override
	public String sayHello(String txt) {
		// TODO Auto-generated method stub
		return txt;
	}

}
