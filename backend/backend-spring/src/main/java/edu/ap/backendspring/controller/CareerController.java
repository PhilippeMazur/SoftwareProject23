package edu.ap.backendspring.controller;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.entity.Career;
import edu.ap.backendspring.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application/career")
public class CareerController {
    @Autowired
    CareerService careerService;

    @GetMapping("")
    public ResponseEntity<List<Career>> getAllCareers(){
        List<Career> careers = careerService.findAllCareers();
        if(careers.iterator().hasNext()) {
            return new ResponseEntity<>(careers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareerById(@PathVariable("id") Integer id){
        Optional<Career> career = careerService.findById(id);
        return career.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("applicationId/{id}")
    public ResponseEntity<List<Career>> getCareersByApplicationId(@PathVariable("id") Integer id) {
        List<Career> allCareers = this.careerService.findAllCareersByApplicationId(id);
        return new ResponseEntity<>(allCareers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Career> createCareer(@RequestBody Career career) {
        Career newCareer = this.careerService.createCareer(career);
        return new ResponseEntity<>(newCareer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Career> deleteCareer(@PathVariable int id) {
        Optional<Career> career = careerService.findById(id);
        if(career.isPresent()) {
            careerService.deleteCareerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
