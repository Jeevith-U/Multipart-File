package com.jeevith.fileupload.entity;

import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PdfEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	@SequenceGenerator(name = "id", initialValue = 10, allocationSize = 10, sequenceName = "seqpdfid")
	private int pdfId ;
	private String pdfName ;
	
	@Lob
	@Column(name = "pdf_data", columnDefinition = "LONGBLOB")
	private byte [] pdfData ;
}
