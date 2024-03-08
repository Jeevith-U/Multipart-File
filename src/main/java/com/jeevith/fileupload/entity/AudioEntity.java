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
public class AudioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	@SequenceGenerator(name = "id", initialValue = 30, allocationSize = 1, sequenceName = "seqaudioid")
	private int audioId ;
	private String audioName ;
	@Lob
	@Column(name = "image_data", columnDefinition = "LONGBLOB")
	private byte [] audioData ;
}
