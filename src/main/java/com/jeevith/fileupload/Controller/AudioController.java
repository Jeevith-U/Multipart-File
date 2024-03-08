package com.jeevith.fileupload.Controller;

import java.io.IOException;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.jeevith.fileupload.entity.AudioEntity;
import com.jeevith.fileupload.service.AudioService;

@RestController
public class AudioController {
	
	@Autowired
	private AudioService service ;
	
	private static final Logger logger = LoggerFactory.getLogger(AudioController.class);
	
	@PostMapping("audio")
	public ResponseEntity<String> saveAudioEntity(@RequestParam ("audio") MultipartFile file) throws IOException{
		
		if (!file.isEmpty()) {
			service.saveAudioEntity(file) ;
			return new ResponseEntity<String>("Saved the Audio 🎧 Successfully...!", HttpStatus.CREATED) ;
		}
		else return new ResponseEntity<String>("Unable to save the Audio File...", HttpStatus.BAD_REQUEST) ;
	}
	
	@GetMapping("audio/{id}")
	public ResponseEntity<byte []>  findAudioEntity(@PathVariable int id){
		
		logger.info("Found the Object") ;
		
		Optional<AudioEntity> audio = service.findAudioEntity(id) ;
		
		if (audio.isPresent()) {
			
			MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM ;
			return ResponseEntity.ok()
					.contentType(mediaType)
					.body(audio.get().getAudioData()) ;
		}else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
	}
	
	@PutMapping("audio/{id}")
	public ResponseEntity<String> updateAudioEntity(@RequestParam ("audio") MultipartFile file,
								@PathVariable int id) throws IOException{
		
		if (!file.isEmpty() && findAudioEntity(id)!= null) {
			service.updateAudioEntity(file, id) ;
			return new ResponseEntity<String>("Updation is done...👍", HttpStatus.OK) ;
		}else return new ResponseEntity<String>("Updation if Failed ❌", HttpStatus.BAD_REQUEST) ;
	}
	
	@DeleteMapping("audio/{id}")
	public ResponseEntity<String> deleteAudioEntity(@PathVariable int id){
		
		logger.warn("You are Deleting the Entity..") ;
		boolean flag = service.deleteAudioEntity(id) ;
		
		if (flag) return new ResponseEntity<String>("Deleted Successfully..", HttpStatus.NO_CONTENT) ;
		else return  new ResponseEntity<String>("Deleted Successfully..", HttpStatus.NO_CONTENT) ;
	}
	
	
}
