package com.jeevith.fileupload.service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeevith.fileupload.dao.VideoDao;
import com.jeevith.fileupload.entity.VideoEntity;

@Service
public class VideoService {
	
	@Autowired
	private VideoDao dao ;
	
	public VideoEntity saveVideoEntity(MultipartFile file) throws IOException {
		
		if (file != null) {
			VideoEntity entity = new VideoEntity() ;
			
			entity.setVideoName(Objects.requireNonNull(file.getOriginalFilename()));
			entity.setVideoData(file.getBytes());
			return dao.saveVideoEntity(entity) ;
		}else return null ;
		
	}
	
	public VideoEntity findVideoEntity(int id) {
		
		Optional<VideoEntity> video = dao.findVideoEntity(id) ;
		
		if(video.isPresent()) return video.get() ;
		else return null ;
	}
	
	public VideoEntity updateVideoEntity(MultipartFile file, int id) throws IOException {
		
		VideoEntity video = findVideoEntity(id) ;
		
		if (!file.isEmpty() && video != null) {
			
			video.setVideoName(Objects.requireNonNull(file.getOriginalFilename()));
			video.setVideoData(file.getBytes());
			return dao.saveVideoEntity(video) ;
		}
		
		else return null ;
	}
	
	public boolean deleteVideoEntity(int id) {
		
		VideoEntity video = findVideoEntity(id) ;
		
		if (video != null) return dao.deleteVideoEntity(id) ;
		else return false ;
	}
}
