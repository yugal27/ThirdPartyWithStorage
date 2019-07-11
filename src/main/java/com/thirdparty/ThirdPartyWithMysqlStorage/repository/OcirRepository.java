package com.thirdparty.ThirdPartyWithMysqlStorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thirdparty.ThirdPartyWithMysqlStorage.models.OciReport;

public interface OcirRepository extends JpaRepository<OciReport, Long> {

}
