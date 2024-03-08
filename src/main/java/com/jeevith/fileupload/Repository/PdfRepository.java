package com.jeevith.fileupload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeevith.fileupload.entity.PdfEntity;

@Repository
public interface PdfRepository extends JpaRepository<PdfEntity, Integer> {

}
