package com.jeevith.fileupload.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class ImageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int imageId ;
	private String imageName ;
	
	@Lob
	@Column(name = "image_data", columnDefinition = "LONGBLOB")
	private byte [] imageData ;
	
	
}
