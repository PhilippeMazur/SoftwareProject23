package edu.ap.backendspring.service;

import edu.ap.backendspring.entity.Certificate;
import edu.ap.backendspring.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CertificateService {

    @Autowired
    public
    CertificateRepository certificateRepository;

    public List<Certificate> findAllCertificates(){
        return certificateRepository.findAll();
    }

    public Optional<Certificate> findCertificateById(@PathVariable("id") Integer id){
        return certificateRepository.findById(id);
    }
    public Optional<Certificate> findCertificateByApplicationId(@PathVariable("id") Integer id){
        return StreamSupport.stream(this.findAllCertificates().spliterator(), false)
                .filter((certificate -> certificate.getApplication().getId() == id)).findFirst();
    }

    public Certificate createCertificate(Certificate certificate){
        if(arePropertiesValid(certificate))
            return certificateRepository.save(certificate);
        else
            throw new IllegalArgumentException("Cannot create the certificate. Please provide values for the required fields.");
    }


    public boolean arePropertiesValid(Certificate certificate) {
        return StringUtils.hasText(certificate.getGenderApplicant())
                && StringUtils.hasText(certificate.getGenderApplicant())
                && StringUtils.hasText(certificate.getJobTitleOption())
                && StringUtils.hasText(certificate.getManagement())
                && certificate.getTotYearService() > 0
                && certificate.getApplication() != null;
    }
}
