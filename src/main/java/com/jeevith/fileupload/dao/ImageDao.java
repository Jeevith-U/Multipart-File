package com.jeevith.fileupload.dao;

import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jeevith.fileupload.Repository.ImageRepository;
import com.jeevith.fileupload.entity.ImageEntity;

@Repository
public class ImageDao {
	
	@Autowired
	private ImageRepository repository ;
	
	public void saveImage(ImageEntity image) throws IOException {
		
		repository.save(image) ;
	}
	
	public ImageEntity findImage(int id) {
		return repository.findById(id).get() ;
	}
	
	public boolean deleteImage(int id) {
		
		ImageEntity image = findImage(id) ;
		
		if (image != null) {

			repository.delete(image);
			return true;
		}
		else return false ;
	}
	
	public String encodeService(byte [] data) {
		
		return Base64.getEncoder().encodeToString(data) ;
	}
}
