package com.thirdparty.ThirdPartyWithMysqlStorage.models;

import java.io.IOException;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thirdparty.ThirdPartyWithMysqlStorage.utils.ReportCustomDeserializer;
import com.thirdparty.ThirdPartyWithMysqlStorage.utils.ReportCustomSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"id", "updatedAt"}, 
allowGetters = true)
@Component
@Getter
@Setter
@ToString
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
    @JsonSerialize(using = ReportCustomSerializer.class)
    @JsonDeserialize(using = ReportCustomDeserializer.class)
    private byte[] report;
	
    @Lob
    @Column(length=100000)
    @JsonProperty("reportPubId")
    @JsonSerialize(using = ReportCustomSerializer.class)
    @JsonDeserialize(using = ReportCustomDeserializer.class)
    private byte[] reportPubId;
    
	@Column(nullable = false)
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	/*@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nreportId:"+reportId);
		sb.append("\nactor:"+actor);
		sb.append("\nreport:"+report.toString());
		return sb.toString();
	}*/
	
	public static void main(String[] args) throws IOException {
		OciReport ocir = new OciReport();
		ocir.setActor("yugal");
		ocir.setId(12345678L);
		ocir.setReportId("12345678");
		ocir.setReport("xml".getBytes());
		ocir.setReportPubId("yml".getBytes());
		System.out.println(ocir);
		ObjectMapper mp = new ObjectMapper();
		System.out.println(mp.writeValueAsString(ocir));
		String xml = "{\"id\":12345678,\"updatedAt\":null,\"reportId\":\"12345678\",\"actor\":\"yugal\",\"report\":\"xml\",\"reportPubId\":\"yml\"}"; 
		System.out.println(mp.readValue(xml, OciReport.class));
	}
}
