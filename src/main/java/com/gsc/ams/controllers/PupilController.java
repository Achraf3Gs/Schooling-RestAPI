package com.gsc.ams.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsc.ams.entities.Pupil;
import com.gsc.ams.entities.Teacher;
import com.gsc.ams.repositories.PupilRepository;
import com.gsc.ams.repositories.TeacherRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping({"/students"})
public class PupilController {
	
	private final PupilRepository pupilRepository;
	
    
	@Autowired
    public PupilController(PupilRepository pupilRepository) {
        this.pupilRepository = pupilRepository;
	}
    
	/*@Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProviderRepository providerRepository;*/

    @GetMapping("/list")
    public List<Pupil> getAllPupils() {
        return (List<Pupil>) pupilRepository.findAll();
    }

    @PostMapping("/addstudent")
    Pupil createPupil( @Valid @RequestBody Pupil pupil) {
			
			return pupilRepository.save(pupil);
		
			}
    
    @PatchMapping("/update/{pupilId}")
    public Pupil updateArticle(
                                 @PathVariable (value = "pupilId") Long pupilId,
                                 @Valid @RequestBody Pupil pupilRequest) {
        

        return pupilRepository.findById(pupilId).map(pupil -> {
        	 pupil.setFirstname(pupilRequest.getFirstname());
        	 pupil.setLastname(pupilRequest.getLastname());
        	 pupil.setPhone(pupilRequest.getPhone()); 
             pupil.setEmail(pupilRequest.getEmail()); 
             pupil.setAge(pupilRequest.getAge()); 
        return pupilRepository.save(pupil);
        }).orElseThrow(() -> new IllegalArgumentException("PupilId " + pupilId + "not found"));
    }
    
    @GetMapping("/{pupilId}")
    public Pupil getPupil(@PathVariable Long pupilId) {
    	
    	Optional<Pupil> p = pupilRepository.findById(pupilId);
    	return p.get();
    }
    
    @DeleteMapping("/delete/{pupilId}")
    public Pupil deletepupil(@PathVariable (value = "pupilId") Long pupilId) {
        return pupilRepository.findById(pupilId).map(pupil -> {
            pupilRepository.delete(pupil);
            return pupil;
        }).orElseThrow(() -> new IllegalArgumentException("Pupil not found with id " + pupilId));
    }
}
