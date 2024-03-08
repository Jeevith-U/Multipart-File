package com.jeevith.fileupload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jeevith.fileupload.entity.VideoEntity;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Integer>{

}
