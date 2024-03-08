package com.jeevith.fileupload.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeevith.fileupload.Repository.AudioRepository;
import com.jeevith.fileupload.entity.AudioEntity;

@Repository
public class AudioDao {
	
	@Autowired
	private AudioRepository repository ;
	
	public AudioEntity saveAudioEntity(AudioEntity entity) {
		return repository.save(entity) ;
	}
	
	public Optional<AudioEntity> findAudioEntity(int id) {
		return repository.findById(id) ;
	}
	
	public boolean deleteAudioEntity(int id) {
		
		Optional<AudioEntity> audio = findAudioEntity(id) ;
		
		if (audio.isPresent()) {
			repository.delete(audio.get());
			return true ;
		}
		else return false ;
	}
}
