package com.thirdparty.ThirdPartyWithMysqlStorage.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thirdparty.ThirdPartyWithMysqlStorage.utils.ReportCustomDeserializer;
import com.thirdparty.ThirdPartyWithMysqlStorage.utils.ReportCustomSerializer;


@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"id", "updatedAt"}, 
allowGetters = true)
@Component
//@Getter
//@Setter
//@ToString
public class OciReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    @Column(length=100000)
    private byte[] report;
	
	@Column(nullable = false)
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	public Long getId() {
		return id;
	}

	public byte[] getReport() {
		return report;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReport(byte[] report) {
		this.report = report;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nreport:"+report.toString());
		return sb.toString();
	}
}
