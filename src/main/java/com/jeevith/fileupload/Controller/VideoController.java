package com.jeevith.fileupload.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.jeevith.fileupload.entity.VideoEntity;
import com.jeevith.fileupload.service.VideoService;

@RestController
public class VideoController {
	
	@Autowired
	private VideoService service ;
	
	@PostMapping("/video")
	public ResponseEntity<String> saveVideoEntity(@RequestParam ("video")MultipartFile file) throws Exception{
		
		if (!file.isEmpty()) {
			service.saveVideoEntity(file) ;
			return new ResponseEntity<String>("Video 🎥 Saved to DB Successfully... 👍", HttpStatus.CREATED) ;
		}
		else throw new Exception("Unable to save The Video") ;
	}
	
	@GetMapping("video/{id}")
	public ResponseEntity<StreamingResponseBody> findVideo(@PathVariable int id) {
	    VideoEntity video = service.findVideoEntity(id);

	    if (video != null) {
	        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType);
	        headers.setContentLength(video.getVideoData().length); // Assuming video.getVideoData() returns the byte array

	        StreamingResponseBody responseBody = outputStream -> {
	            outputStream.write(video.getVideoData());
	        };

	        return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
	    }
		else  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PutMapping("video/{id}")
	public ResponseEntity<String> updateVideoEntity(@RequestParam ("video") MultipartFile file,
			@PathVariable int id) throws IOException {
		
		if (!file.isEmpty() && findVideo(id) != null) {
			service.updateVideoEntity(file, id) ;
			return new ResponseEntity<String>("Updated Succesfully...!👍", HttpStatus.OK) ;
		}else return new ResponseEntity<String>("Unable to Update The content 🥲", HttpStatus.NO_CONTENT) ;
	}
	
	@DeleteMapping("video/{id}")
	public ResponseEntity<String> deleteVideoEntity(@PathVariable int id){
		
		VideoEntity video = service.findVideoEntity(id) ;
		
		if (video != null) {
			service.deleteVideoEntity(id) ;
			return new ResponseEntity<String>("Video Deleted Successfully...😉", HttpStatus.OK) ;
		}
		else return new ResponseEntity<String>("Unable to Deleted Successfully...🫡", HttpStatus.NO_CONTENT) ;
	}
}
