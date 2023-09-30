package com.gsc.ams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsc.ams.entities.Pupil;



public interface PupilRepository extends JpaRepository<Pupil, Long> {

}
