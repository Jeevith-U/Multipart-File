package com.jeevith.fileupload.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeevith.fileupload.Repository.PdfRepository;
import com.jeevith.fileupload.entity.PdfEntity;

@Repository
public class PdfDao {
	
	@Autowired
	private PdfRepository repository ;
	
	public PdfEntity savePDF(PdfEntity pdf) {
		return repository.save(pdf) ;
	}
	
	public Optional<PdfEntity> findPdf(int id) {
		return repository.findById(id) ;
	}
	
	public boolean deletePdf(int id) {
		Optional<PdfEntity> pdf = findPdf(id) ;
		
		if (pdf.isPresent()) {
			repository.delete(pdf.get());
			return true ;
		}
		else return false ;
	}
	
}
