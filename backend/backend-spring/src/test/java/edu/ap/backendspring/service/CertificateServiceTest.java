package edu.ap.backendspring.service;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.entity.Certificate;
import edu.ap.backendspring.repository.CertificateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CertificateServiceTest {

    @MockBean
    private CertificateRepository certificateRepository;

    private CertificateService certificateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        certificateService = new CertificateService();
        certificateService.certificateRepository = certificateRepository;
    }

    @Test
    public void testFindAllCertificates() {
        // Arrange
        List<Certificate> expectedCertificates = new ArrayList<>();
        expectedCertificates.add(new Certificate());
        when(certificateRepository.findAll()).thenReturn(expectedCertificates);

        // Act
        List<Certificate> actualCertificates = certificateService.findAllCertificates();

        // Assert
        Assert.notNull(actualCertificates, "Certificates should not be null");
        Assert.isTrue(actualCertificates.size() == 1, "Expected 1 certificate, but found " + actualCertificates.size());
        Assert.isTrue(actualCertificates.get(0) instanceof Certificate, "Expected Certificate instance");
        verify(certificateRepository, times(1)).findAll();
    }

    @Test
    public void testFindCertificateById() {
        // Arrange
        Certificate expectedCertificate = new Certificate();
        int id = 1;
        when(certificateRepository.findById(id)).thenReturn(Optional.of(expectedCertificate));

        // Act
        Optional<Certificate> actualCertificate = certificateService.findCertificateById(id);

        // Assert
        Assert.isTrue(actualCertificate.isPresent(), "Certificate should be present");
        Assert.isTrue(actualCertificate.get() instanceof Certificate, "Expected Certificate instance");
        verify(certificateRepository, times(1)).findById(id);
    }

    @Test
    public void testFindCertificateByApplicationId() {
        // Arrange
        int applicationId = 1;
        List<Certificate> certificates = new ArrayList<>();
        Certificate certificate1 = new Certificate();
        certificate1.setApplication(new Application());
        certificate1.getApplication().setId(applicationId);
        certificates.add(certificate1);
        Certificate certificate2 = new Certificate();
        certificate2.setApplication(new Application());
        certificate2.getApplication().setId(2);
        certificates.add(certificate2);
        when(certificateRepository.findAll()).thenReturn(certificates);

        // Act
        Optional<Certificate> actualCertificate = certificateService.findCertificateByApplicationId(applicationId);

        // Assert
        Assert.isTrue(actualCertificate.isPresent(), "Certificate should be present");
        Assert.isTrue(actualCertificate.get() instanceof Certificate, "Expected Certificate instance");
        Assert.isTrue(actualCertificate.get().getApplication().getId() == applicationId, "Expected certificate with applicationId");
        verify(certificateRepository, times(1)).findAll();
    }

    @Test
    public void testCreateCertificate_ValidCertificate() {
        // Arrange
        Certificate certificate = new Certificate();
        certificate.setGenderApplicant("male");
        certificate.setManagement("management");
        certificate.setTotYearService(25);
        certificate.setJobTitleOption("option");
        certificate.setApplication(new Application());
        when(certificateRepository.save(certificate)).thenReturn(certificate);

        // Act
        Certificate createdCertificate = certificateService.createCertificate(certificate);

        // Assert
        Assert.notNull(createdCertificate, "Created certificate should not be null");
        verify(certificateRepository, times(1)).save(certificate);
    }


    @Test
    public void testArePropertiesValid_InvalidCertificate() {
        // Arrange
        Certificate invalidCertificate = new Certificate();

        // Act
        boolean result = certificateService.arePropertiesValid(invalidCertificate);

        // Assert
        assertFalse(result);
    }
}