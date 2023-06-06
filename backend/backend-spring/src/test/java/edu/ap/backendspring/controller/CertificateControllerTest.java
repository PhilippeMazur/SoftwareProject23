package edu.ap.backendspring.controller;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.entity.Certificate;
import edu.ap.backendspring.enums.State;
import edu.ap.backendspring.repository.CertificateRepository;
import edu.ap.backendspring.service.CertificateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CertificateControllerTest {

    @Mock
    private CertificateRepository certificateRepository;

    private CertificateService certificateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        certificateService = new CertificateService();
        certificateService.certificateRepository = certificateRepository;
    }

    @Test
    void testFindAllCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        certificates.add(new Certificate());
        certificates.add(new Certificate());
        when(certificateRepository.findAll()).thenReturn(certificates);

        List<Certificate> result = certificateService.findAllCertificates();

        assertEquals(certificates.size(), result.size());
        verify(certificateRepository, times(1)).findAll();
    }

    @Test
    void testFindCertificateById() {
        Certificate certificate = new Certificate();
        int id = 1;
        when(certificateRepository.findById(id)).thenReturn(Optional.of(certificate));

        Optional<Certificate> result = certificateService.findCertificateById(id);

        assertTrue(result.isPresent());
        assertEquals(certificate, result.get());
        verify(certificateRepository, times(1)).findById(id);
    }

    @Test
    void testFindCertificateByApplicationId() {
        int applicationId = 1;
        LocalDate birthdate = LocalDate.of(1950,03,13);
        LocalDate creationDate = LocalDate.of(2023,5,22);
        Application newApplication = new Application("1538234671", "philippe", "mazur", birthdate, "Antwerpen", "Bazel", "Student", "mandataris", "geen", "geen", "geen", 2, 5, "geen", "geen", State.KLAAR, "geen", "geen", "geen", "geen", "geen", "geen", null, null, null, null, null, creationDate, null, null, null, null, null, null, "distinction");
        newApplication.setId(applicationId);
        List<Certificate> certificates = new ArrayList<>();
        certificates.add(new Certificate("Male", "Winkel bediende", "bestuur", 10, newApplication));
        when(certificateRepository.findAll()).thenReturn(certificates);

        Optional<Certificate> result = certificateService.findCertificateByApplicationId(applicationId);

        assertTrue(result.isPresent());
        verify(certificateRepository, times(1)).findAll();
    }

    @Test
    void testCreateCertificate_ValidCertificate() {
        Certificate certificate = new Certificate();
        certificate.setGenderApplicant("Male");
        certificate.setJobTitleOption("Winkel bediende");
        certificate.setManagement("bestuur");
        certificate.setTotYearService(5);
        certificate.setApplication(new Application());

        when(certificateRepository.save(certificate)).thenReturn(certificate);

        Certificate result = certificateService.createCertificate(certificate);

        assertEquals(certificate, result);
        verify(certificateRepository, times(1)).save(certificate);
    }

    @Test
    void testCreateCertificate_InvalidCertificate() {
        Certificate certificate = new Certificate();

        assertThrows(IllegalArgumentException.class, () -> certificateService.createCertificate(certificate));
        verify(certificateRepository, never()).save(certificate);
    }
}