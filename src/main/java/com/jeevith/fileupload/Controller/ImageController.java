package com.jeevith.fileupload.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jeevith.fileupload.entity.ImageEntity;
import com.jeevith.fileupload.service.ImageService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class ImageController {
	
	@Autowired
	private ImageService service ;
	
	@PostMapping("/image")
	public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file) throws IOException {
		
		if (!file.isEmpty()) {
			service.saveImage(file) ;
			return new ResponseEntity<String>("Saved to DB Successfully...", HttpStatus.CREATED) ;
		}else return new ResponseEntity<String>("Saved to DB Successfully...", HttpStatus.BAD_REQUEST) ;
		
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> findImage(@PathVariable int id){
		
		ImageEntity image = service.findImage(id) ;
		
		if(image != null) {
			MediaType mediaType = MediaType.IMAGE_JPEG ;
			return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(image.getImageData());
		}else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
	}
	
	@PutMapping("/image/{id}")
		public ResponseEntity<String> updateImage(@RequestParam("file") MultipartFile file, 
				@PathVariable int id) throws IOException{
		
		if (findImage(id) != null) {
			service.UpdateImage(file, id) ;
			return new ResponseEntity<String>("Updated the Image", HttpStatus.OK) ;
		}else
			return new ResponseEntity<String>("Unable to the Update", HttpStatus.NOT_FOUND) ;
		
			
	}
	
	@DeleteMapping("/image/{id}")
	public ResponseEntity<String> deleteImage(@PathVariable int id){
		System.out.println(id);
		boolean flag = service.deleteImage(id) ;
		if (flag)
		 return new ResponseEntity<String>(HttpStatus.NO_CONTENT) ;
		else return new ResponseEntity<String>("Unable to delete...!", HttpStatus.BAD_REQUEST) ;
	}
	
}
