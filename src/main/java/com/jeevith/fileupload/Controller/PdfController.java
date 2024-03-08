package com.jeevith.fileupload.Controller;

import java.io.IOException;
import java.util.Optional;

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
import com.jeevith.fileupload.entity.PdfEntity;
import com.jeevith.fileupload.service.PdfService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class PdfController {
	
	@Autowired
	private PdfService service ;
	
	@PostMapping("/pdf")
	public ResponseEntity<String> savePdfEntity(@RequestParam ("pdf") MultipartFile file) throws Exception{
		
		if(!file.isEmpty()) return new ResponseEntity<String>("Saved to the DB Succesfully...✅", HttpStatus.CREATED) ;
		else throw new Exception("Unable to Save the Data to the Database") ;
	}
	
	@GetMapping("/pdf/{id}")
	public ResponseEntity<byte[]> findPdf(@PathVariable int id){
		
		 Optional<PdfEntity> pdf = service.findPdf(id) ;
		
		if(pdf.isPresent()) {
			MediaType media = MediaType.APPLICATION_PDF ;
			return ResponseEntity.ok()
                    .contentType(media)
                    .body(pdf.get().getPdfData());
		}else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
	}
	
	@PutMapping("/pdf/{id}")
	public ResponseEntity<String> updatePdf(@RequestParam ("pdf") MultipartFile file, @PathVariable int id) throws IOException{
		
		PdfEntity pdf = service.updatePdf(file, id) ;
		
		if (pdf != null) 
			return new ResponseEntity<String>("Updated Successfully...👍", HttpStatus.OK) ;
		else return new ResponseEntity<String>("Unable to Update...", HttpStatus.BAD_REQUEST) ;
	}
	
	@DeleteMapping("/pdf/{id}")
	public ResponseEntity<String> deletePdf(@PathVariable int id){
		
		boolean flag = service.deletePdf(id) ;
		
		if (flag) return new ResponseEntity<String>("Deleted Successfully..!", HttpStatus.NO_CONTENT) ;
		else return new ResponseEntity<String>("Unable to Update...", HttpStatus.BAD_REQUEST)  ;
	}
}
