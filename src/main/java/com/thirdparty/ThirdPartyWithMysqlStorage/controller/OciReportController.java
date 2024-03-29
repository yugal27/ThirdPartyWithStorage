package com.thirdparty.ThirdPartyWithMysqlStorage.controller;

import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thirdparty.ThirdPartyWithMysqlStorage.models.OciReport;
import com.thirdparty.ThirdPartyWithMysqlStorage.repository.OcirRepository;
import com.thirdparty.ThirdPartyWithMysqlStorage.service.ReportDumpWorker;

@RequestMapping("/api")
@RestController
public class OciReportController {
	
	private static Logger HTTP_LOGGER = Logger.getLogger("httpLogger");
	
	@Autowired
    @Qualifier("customThreadPool")
    private ExecutorService executorService;
	
	@Autowired
	OcirRepository ocirRepository;
	
	@Autowired
	private ApplicationContext appContext;
	
	@GetMapping("/ping")
	String ping() {
	    return "Keep-Alive";
	}
	
	@PutMapping("/report/{id}")
	void storeReport(@RequestBody String report, @PathVariable String id) {
		HTTP_LOGGER.info(report.toString());
		OciReport ocir = appContext.getBean(OciReport.class);
		ocir.setReport(report.getBytes());
		ocirRepository.save(ocir);
		executorService.submit(new ReportDumpWorker(ocirRepository,ocir)); 
			
	}
}
