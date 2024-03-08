package com.jeevith.fileupload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeevith.fileupload.entity.AudioEntity;

@Repository
public interface AudioRepository extends JpaRepository<AudioEntity, Integer>{

}
