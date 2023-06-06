package edu.ap.backendspring.controller;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> allApplications = applicationService.findAllApplications();
        if(allApplications.iterator().hasNext()) {
            return new ResponseEntity<>(allApplications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable int id) {
        Optional<Application> application = this.applicationService.findApplicationById(id);
        return application.map(existingApplication -> new ResponseEntity<>(existingApplication, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        this.applicationService.createApplication(application);
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable int id, @RequestBody Application application) {
        Application updateApplication = applicationService.updateApplication(id, application);
        return new ResponseEntity<>(updateApplication, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Application> deleteApplication(@PathVariable int id) {
        applicationService.deleteApplicationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}