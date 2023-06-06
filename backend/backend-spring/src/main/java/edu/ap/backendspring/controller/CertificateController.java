package edu.ap.backendspring.controller;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.entity.Career;
import edu.ap.backendspring.entity.Certificate;
import edu.ap.backendspring.service.CareerService;
import edu.ap.backendspring.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @GetMapping("")
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        List<Certificate> allCertificates = certificateService.findAllCertificates();
        if(allCertificates.iterator().hasNext()) {
            return new ResponseEntity<>(allCertificates, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable int id) {
        Optional<Certificate> certificate = this.certificateService.findCertificateById(id);
        return certificate.map(existingApplication -> new ResponseEntity<>(existingApplication, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("applicationId/{id}")
    public ResponseEntity<Optional<Certificate>> getCertificateByApplicationId(@PathVariable("id") Integer id) {
        Optional<Certificate> certificate = this.certificateService.findCertificateByApplicationId(id);
        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate certificate) {
       Certificate newCertificate =  this.certificateService.createCertificate(certificate);
        return new ResponseEntity<>(newCertificate, HttpStatus.CREATED);
    }
}
