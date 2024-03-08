package com.jeevith.fileupload.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeevith.fileupload.Repository.ImageRepository;
import com.jeevith.fileupload.dao.ImageDao;
import com.jeevith.fileupload.entity.ImageEntity;

@Service
public class ImageService {
	
	@Autowired
	private ImageDao dao ;
	
	public void saveImage(MultipartFile file) throws IOException {
		ImageEntity image = new ImageEntity() ;
		
		image.setImageName(Objects.requireNonNull(file.getOriginalFilename()));
		image.setImageData(file.getBytes());
		
		dao.saveImage(image) ;
	}
	
	public ImageEntity findImage(int id) {
		return dao.findImage(id) ;
	}
	
	public void UpdateImage(MultipartFile file, int id) throws IOException {
		
		ImageEntity image = findImage(id) ;
		
		image.setImageName(Objects.requireNonNull(file.getOriginalFilename()));
		image.setImageData(file.getBytes());
		
		dao.saveImage(image) ;
	}
	
	public boolean deleteImage(int id) {
		
		ImageEntity image = findImage(id) ;
		if (image != null) {
			dao.deleteImage(id) ;
			return true ;
		}else return false ;
	}
	
	public String encodeService(byte [] data) {
		
		return Base64.getEncoder().encodeToString(data) ;
	}
}
