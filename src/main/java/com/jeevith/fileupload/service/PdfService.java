package com.jeevith.fileupload.service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeevith.fileupload.dao.PdfDao;
import com.jeevith.fileupload.entity.PdfEntity;

@Service
public class PdfService {
	
	@Autowired
	private PdfDao dao ;
	
	public PdfEntity savePdf(MultipartFile file) throws IOException {
		
		PdfEntity pdf = new PdfEntity() ;
		
		pdf.setPdfName(Objects.requireNonNull(file.getOriginalFilename()));
		pdf.setPdfData(file.getBytes());
		
		return dao.savePDF(pdf);
	}
	
	public Optional<PdfEntity> findPdf(int id) {
		
		if(dao.findPdf(id).isPresent() ) 
			return dao.findPdf(id) ;
		else return null ;
	}
	
	public PdfEntity updatePdf(MultipartFile file, int id) throws IOException {
		
		Optional<PdfEntity> pdf = findPdf(id) ;
		
		pdf.get().setPdfName(file.getOriginalFilename());
		pdf.get().setPdfData(file.getBytes());
		
		return dao.savePDF(pdf.get()) ;
	}
	
	public boolean deletePdf(int id) {
		
		Optional<PdfEntity> pdf = findPdf(id) ;
		
		if (pdf.isPresent()) return dao.deletePdf(id) ;
		else return false ;
	}
}
