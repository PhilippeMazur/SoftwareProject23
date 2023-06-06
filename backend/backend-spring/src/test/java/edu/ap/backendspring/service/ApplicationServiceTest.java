package edu.ap.backendspring.service;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.enums.State;
import edu.ap.backendspring.repository.ApplicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllApplications() {
        // Arrange
        List<Application> expectedApplications = new ArrayList<>();
        expectedApplications.add(new Application());
        Mockito.when(applicationRepository.findAll()).thenReturn(expectedApplications);

        // Act
        Iterable<Application> actualApplications = applicationService.findAllApplications();

        // Assert
        Assertions.assertEquals(expectedApplications, actualApplications);
    }

    @Test
    public void testFindApplicationById() {
        // Arrange
        int applicationId = 1;
        Application expectedApplication = new Application();
        expectedApplication.setId(applicationId);
        Mockito.when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(expectedApplication));

        // Act
        Optional<Application> actualApplication = applicationService.findApplicationById(applicationId);

        // Assert
        Assertions.assertTrue(actualApplication.isPresent());
        Assertions.assertEquals(expectedApplication, actualApplication.get());
    }

    @Test
    public void testCreateApplication() {
        // Arrange
        LocalDate creationDate = LocalDate.of(2007,6,30);
        LocalDate birthdate = LocalDate.of(1950,03,13);

        Application applicationToCreate = new Application();
        applicationToCreate.setLastname("Janssen");
        applicationToCreate.setFirstname("Jan");
        applicationToCreate.setJobTitle("Ingenieur");
        applicationToCreate.setBirthdate(birthdate);
        applicationToCreate.setBirthplace("Lanaken");
        applicationToCreate.setCity("Belsele");
        applicationToCreate.setMainProfession("unknown");
        applicationToCreate.setState(State.BESLUIT);
        applicationToCreate.setInitiator("Thomas");
        applicationToCreate.setNationalRegisterNr("75011401234");
        applicationToCreate.setTotYearService(20);
        applicationToCreate.setTotMonthService(2);
        applicationToCreate.setGradeOrRank("Ig");

        Mockito.when(applicationRepository.save(applicationToCreate)).thenReturn(applicationToCreate);

        // Act
        Application createdApplication = applicationService.createApplication(applicationToCreate);

        // Assert
        Assertions.assertEquals(applicationToCreate, createdApplication);
    }

    @Test
    public void testCreateApplicationWithMissingRequiredFields() {
        // Arrange
        Application applicationToCreate = new Application();

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> applicationService.createApplication(applicationToCreate));
    }

    @Test
    public void testUpdateApplication() {
        // Arrange
        int applicationId = 1;
        Application existingApplication = new Application();
        existingApplication.setId(applicationId);
        existingApplication.setState(State.GOEDKEURING_1);
        Mockito.when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(existingApplication));
        Application applicationToUpdate = new Application();
        applicationToUpdate.setId(applicationId);
        applicationToUpdate.setState(State.GOEDKEURING_2);

        // Act
        Application updatedApplication = applicationService.updateApplication(applicationId, applicationToUpdate);
        Optional<Application> retrievedApplication = applicationService.findApplicationById(applicationId);

        // Assert
        Assertions.assertTrue(retrievedApplication.isPresent());
        Assertions.assertEquals(applicationToUpdate.getState(), retrievedApplication.get().getState());
    }

    @Test
    public void testUpdateApplicationWithNonExistingId() {
        // Arrange
        int applicationId = 1;
        Application applicationToUpdate = new Application();

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> applicationService.updateApplication(applicationId, applicationToUpdate));
    }

    @Test
    public void testDeleteApplicationById() {
        // Arrange
        int applicationId = 1;
        Application existingApplication = new Application();
        existingApplication.setId(applicationId);
        Mockito.when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(existingApplication));

        // Act
        Optional<Application> deletedApplication = applicationService.deleteApplicationById(applicationId);

        // Assert
        Assertions.assertTrue(deletedApplication.isPresent());
        Assertions.assertEquals(existingApplication, deletedApplication.get());

        // Verify
        Mockito.verify(applicationRepository, Mockito.times(1)).deleteById(applicationId);
    }
}