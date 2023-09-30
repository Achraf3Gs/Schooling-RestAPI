package com.gsc.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsc.ams.entities.Pupil;
import com.gsc.ams.repositories.PupilRepository;
import com.gsc.ams.repositories.TeacherRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping({"/pupils"})
public class PupilController {
	
	private final PupilRepository pupilRepository;
	private final TeacherRepository teacherRepository;
    
	@Autowired
    public PupilController(PupilRepository pupilRepository, TeacherRepository teacherRepository) {
        this.pupilRepository = pupilRepository;
        this.teacherRepository = teacherRepository;
    }
	/*@Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProviderRepository providerRepository;*/

    @GetMapping("/list")
    public List<Pupil> getAllPupils() {
        return (List<Pupil>) pupilRepository.findAll();
    }

    @PostMapping("/add/{teacherId}")
    Pupil createArticle(@PathVariable (value = "teacherId") Long teacherId, @Valid @RequestBody Pupil pupil) {
			return teacherRepository.findById(teacherId).map(teacher -> {
			pupil.setTeacher(teacher);
			return pupilRepository.save(pupil);
			}).orElseThrow(() -> new IllegalArgumentException("TeacherId " + teacherId + " not found"));
			}
    
    @PutMapping("/update/{teacherId}/{pupilId}")
    public Pupil updateArticle(@PathVariable (value = "teacherId") Long teacherId,
                                 @PathVariable (value = "pupilId") Long pupilId,
                                 @Valid @RequestBody Pupil pupilRequest) {
        if(!teacherRepository.existsById(teacherId)) {
            throw new IllegalArgumentException("TeacherId " + teacherId + " not found");
        }

        return pupilRepository.findById(pupilId).map(pupil -> {
        	 pupil.setTall(pupilRequest.getTall());
             pupil.setLabel(pupilRequest.getLabel()); 
             pupil.setPicture(pupilRequest.getPicture()); 
        return pupilRepository.save(pupil);
        }).orElseThrow(() -> new IllegalArgumentException("PupilId " + pupilId + "not found"));
    }
    
    @DeleteMapping("/delete/{pupilId}")
    public ResponseEntity<?> deletepupil(@PathVariable (value = "pupilId") Long pupilId) {
        return pupilRepository.findById(pupilId).map(pupil -> {
            pupilRepository.delete(pupil);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Pupil not found with id " + pupilId));
    }
}
