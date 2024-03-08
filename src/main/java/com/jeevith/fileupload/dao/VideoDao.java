package com.jeevith.fileupload.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeevith.fileupload.Repository.VideoRepository;
import com.jeevith.fileupload.entity.VideoEntity;

@Repository
public class VideoDao {
	
	@Autowired
	private VideoRepository repository ;
	
	public VideoEntity saveVideoEntity(VideoEntity entity) {
		
		return repository.save(entity) ;
	}
	
	public Optional<VideoEntity> findVideoEntity(int id) {
		return repository.findById(id) ;
	}
	
	public boolean deleteVideoEntity(int id) {
		
		 Optional<VideoEntity> video = findVideoEntity(id) ;
		
		if(video.isPresent()) {
			repository.delete(video.get());
			return true ;
		} else return false ;
	}
}
