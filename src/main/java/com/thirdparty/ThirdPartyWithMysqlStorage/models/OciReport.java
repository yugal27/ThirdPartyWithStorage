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


@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"id", "updatedAt"}, 
allowGetters = true)
@Component
public class OciReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotBlank
    @JsonProperty("reportId")
	private String reportId;
    
    @NotBlank
    @JsonProperty("actor")
    private String actor;
    
    @Lob
    @Column(length=100000)
    @JsonProperty("report")
    private byte[] report;
	
	@Column(nullable = false)
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nreportId:"+reportId);
		sb.append("\nactor:"+actor);
		sb.append("\nreport:"+report.toString());
		return sb.toString();
	}
}
