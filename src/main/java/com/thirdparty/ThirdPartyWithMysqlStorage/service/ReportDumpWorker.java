package com.thirdparty.ThirdPartyWithMysqlStorage.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.thirdparty.ThirdPartyWithMysqlStorage.models.OciReport;
import com.thirdparty.ThirdPartyWithMysqlStorage.repository.OcirRepository;

@Component
public class ReportDumpWorker implements Runnable {
	
	private static Logger WORKER_LOGGER = Logger.getLogger("workerLogger");

	private OcirRepository ocirRepository;
	private OciReport ociReport;
	public ReportDumpWorker(OcirRepository ocirRepository, OciReport ociReport) {
		this.ocirRepository = ocirRepository;
		this.ociReport = ociReport;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		WORKER_LOGGER.info("Inserting into db");
		OciReport insertedEntity = ocirRepository.save(ociReport);
		WORKER_LOGGER.info("Entity inerted into db : "+insertedEntity.toString());
	}

}
