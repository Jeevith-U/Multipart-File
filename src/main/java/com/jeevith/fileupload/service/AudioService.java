package com.jeevith.fileupload.service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeevith.fileupload.dao.AudioDao;
import com.jeevith.fileupload.entity.AudioEntity;

@Service
public class AudioService {
	
	@Autowired
	private AudioDao dao ;
	
	public AudioEntity saveAudioEntity(MultipartFile file) throws IOException {
		
		if (!file.isEmpty()) {
			AudioEntity entity = new AudioEntity() ;
			entity.setAudioName(Objects.requireNonNull(file.getOriginalFilename()));
			entity.setAudioData(file.getBytes());
			return dao.saveAudioEntity(entity) ;
		}else return null ;
	}
	
	public Optional<AudioEntity> findAudioEntity(int id) {
		
		Optional<AudioEntity> audio = dao.findAudioEntity(id) ;
		if (!audio.isEmpty()) return audio ;
		else return null ;
	}
	
	public AudioEntity updateAudioEntity(MultipartFile file, int id) throws IOException {
		
		 AudioEntity audio = findAudioEntity(id).get() ;
		
		if (!file.isEmpty() && audio != null) {
			audio.setAudioName(Objects.requireNonNull(file.getOriginalFilename()));
			audio.setAudioData(file.getBytes());
			return dao.saveAudioEntity(audio) ;
		}else return null ;
	}
	
	public boolean deleteAudioEntity(int id) {
		
		Optional<AudioEntity> audio = findAudioEntity(id) ;
			
		if (audio.isPresent()) return dao.deleteAudioEntity(id) ;
		else return false ;
		
	}
	
}
