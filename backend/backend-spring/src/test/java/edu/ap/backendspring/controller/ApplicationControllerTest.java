package edu.ap.backendspring.controller;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.enums.State;
import edu.ap.backendspring.repository.ApplicationRepository;
import edu.ap.backendspring.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationControllerTest {
    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllApplications_ShouldReturnAllApplications() {
        List<Application> applications = new ArrayList<>();
        applications.add(new Application());
        applications.add(new Application());
        when(applicationService.findAllApplications()).thenReturn(applications);

        ResponseEntity<List<Application>> response = applicationController.getAllApplications();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(applications, response.getBody());
        verify(applicationService, times(1)).findAllApplications();
    }

    @Test
    void getAllApplications_WhenNoApplicationsExist_ShouldReturnNoContent() {
        when(applicationService.findAllApplications()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Application>> response = applicationController.getAllApplications();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(applicationService, times(1)).findAllApplications();
    }

    @Test
    void getApplicationById_WithValidId_ShouldReturnApplication() {
        int id = 1;
        Application application = new Application();
        when(applicationService.findApplicationById(id)).thenReturn(Optional.of(application));

        ResponseEntity<Application> response = applicationController.getApplicationById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(application, response.getBody());
        verify(applicationService, times(1)).findApplicationById(id);
    }

    @Test
    void getApplicationById_WithInvalidId_ShouldReturnNotFound() {
        int id = 1;
        when(applicationService.findApplicationById(id)).thenReturn(Optional.empty());

        ResponseEntity<Application> response = applicationController.getApplicationById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(applicationService, times(1)).findApplicationById(id);
    }

    @Test
    void createApplication_ShouldCreateApplicationAndReturnCreated() {
        Application application = new Application();

        ResponseEntity<Application> response = applicationController.createApplication(application);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(application, response.getBody());
        verify(applicationService, times(1)).createApplication(application);
    }

    @Test
    void updateApplication_WithValidId_ShouldUpdateApplicationAndReturnUpdated() {
        int id = 1;
        Application application = new Application();
        when(applicationService.updateApplication(id, application)).thenReturn(application);

        ResponseEntity<Application> response = applicationController.updateApplication(id, application);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(application, response.getBody());
        verify(applicationService, times(1)).updateApplication(id, application);
    }

    @Test
    void deleteApplication_WithValidId_ShouldDeleteApplicationAndReturnOk() {
        int id = 1;
        Application application = new Application();
        when(applicationService.findApplicationById(id)).thenReturn(Optional.of(application));

        ResponseEntity<Application> response = applicationController.deleteApplication(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(applicationService, times(1)).deleteApplicationById(id);
    }
}