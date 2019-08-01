package com.thirdparty.ThirdPartyWithMysqlStorage.executor;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.thirdparty.ThirdPartyWithMysqlStorage.models.OciReport;

public class ApplicationBeanConfig {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public OciReport getReport(){
		return new OciReport();
	}
}
