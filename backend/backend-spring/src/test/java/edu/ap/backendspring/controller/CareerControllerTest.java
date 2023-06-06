package edu.ap.backendspring.controller;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.entity.Career;
import edu.ap.backendspring.enums.State;
import edu.ap.backendspring.repository.ApplicationRepository;
import edu.ap.backendspring.service.ApplicationService;
import edu.ap.backendspring.service.CareerService;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CareerControllerTest {
    @Mock
    private CareerService careerService;

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private CareerController careerController;

    @InjectMocks
    private ApplicationController applicationController;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCareers_ReturnsCareersList() {
        LocalDate creationdate1 = LocalDate.of(2007,6,30);
        LocalDate birthdate = LocalDate.of(1950,03,13);
        LocalDate from = LocalDate.of(1980,03,13);
        LocalDate until = LocalDate.of(1990,05,15);
        Application newApplication = new Application("1234", "firstname", "lastname",birthdate, "birthplace", "city", "mainProfession", "jobTitle", "hfk2", "initiatior", "distinctionsRecieved", 12, 3, "result", "sanctions", State.GOEDKEURING_1,"scale",  "proposed", "report", "unknown", "unknown", "unknown", null, null, null, null, null, creationdate1, null, null, null, null, null, null, "distinction");
        List<Career> careers = new ArrayList<>();
        careers.add(new Career("programmer", "A4b1", from, until, 10.0, "naturePerformances", newApplication));
        careers.add(new Career("better programmer", "A4b1", from, until, 10.0, "naturePerformances", newApplication));

        when(careerService.findAllCareers()).thenReturn(careers);

        ResponseEntity<List<Career>> response = careerController.getAllCareers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(careers, response.getBody());
        verify(careerService, times(1)).findAllCareers();
    }

    @Test
    public void testGetAllCareers_ReturnsNoContent() {
        when(careerService.findAllCareers()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Career>> response = careerController.getAllCareers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(careerService, times(1)).findAllCareers();
    }

    @Test
    public void testGetCareerById_ReturnsFound() {
        LocalDate creationdate1 = LocalDate.of(2007,6,30);
        LocalDate birthdate = LocalDate.of(1950,03,13);
        LocalDate from = LocalDate.of(1980,03,13);
        LocalDate until = LocalDate.of(1990,05,15);
        Application newApplication = new Application("1234", "firstname", "lastname",birthdate, "birthplace", "city", "mainProfession", "jobTitle", "hfk2", "initiatior", "distinctionsRecieved", 12, 3, "result", "sanctions", State.GOEDKEURING_1,"scale",  "proposed", "report", "unknown", "unknown", "unknown", null, null, null, null, null, creationdate1, null, null, null, null, null, null, "distinction");
        Career career = new Career("programmer", "A4b1", from, until, 10.0, "naturePerformances", newApplication);
        when(careerService.findById(anyInt())).thenReturn(Optional.of(career));

        ResponseEntity<Career> response = careerController.getCareerById(1);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(career, response.getBody());
        verify(careerService, times(1)).findById(1);
    }

    @Test
    public void testGetCareerById_ReturnsNotFound() {
        when(careerService.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Career> response = careerController.getCareerById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(careerService, times(1)).findById(1);
    }

    @Test
    public void testFindCareersByApplicationId_ReturnsApplication() {
        LocalDate creationdate1 = LocalDate.of(2007,6,30);
        LocalDate birthdate = LocalDate.of(1950,03,13);
        LocalDate from = LocalDate.of(1980,03,13);
        LocalDate until = LocalDate.of(1990,05,15);
        Application newApplication = new Application("1234", "firstname", "lastname",birthdate, "birthplace", "city", "mainProfession", "jobTitle", "hfk2", "initiatior", "distinctionsRecieved", 12, 3, "result", "sanctions", State.GOEDKEURING_1,"scale",  "proposed", "report", "unknown", "unknown", "unknown", null, null, null, null, null, creationdate1, null, null, null, null, null, null, "distinction");
        newApplication.setId(2);
        Career career = new Career("programmer", "A4b1", from, until, 10.0, "naturePerformances", newApplication);
        Career career2 = new Career("better programmer", "A4b1", from, until, 10.0, "naturePerformances", newApplication);
        List<Career> careerList = new ArrayList<>();
        careerList.add(career);
        careerList.add(career2);
        when(careerService.findAllCareersByApplicationId(career.getApplication().getId())).thenReturn(careerList);

        ResponseEntity<List<Career>> response = careerController.getCareersByApplicationId(2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(careerList, response.getBody());
        verify(careerService, times(1)).findAllCareersByApplicationId(2);
    }
    @Test
    public void testCreateCareer_ReturnsCreated() {
        LocalDate creationdate1 = LocalDate.of(2007,6,30);
        LocalDate birthdate = LocalDate.of(1950,03,13);
        LocalDate from = LocalDate.of(1980,03,13);
        LocalDate until = LocalDate.of(1990,05,15);
        Application newApplication = new Application("1234", "firstname", "lastname",birthdate, "birthplace", "city", "mainProfession", "jobTitle", "hfk2", "initiatior", "distinctionsRecieved", 12, 3, "result", "sanctions", State.GOEDKEURING_1,"scale",  "proposed", "report", "unknown", "unknown", "unknown", null, null, null, null, null, creationdate1, null, null, null, null, null, null, "distinction");
        Career career = new Career("programmer", "A4b1", from, until, 10.0, "naturePerformances", newApplication);

        when(careerService.createCareer(any())).thenReturn(career);
        when(applicationService.createApplication(any())).thenReturn(newApplication);
        ResponseEntity<Application> response = applicationController.createApplication(newApplication);
        ResponseEntity<Career> response2 = careerController.createCareer(career);

        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals(career, response2.getBody());
        verify(careerService, times(1)).createCareer(career);
    }
    @Test
    public void testCreateCareer_ReturnsError() {
        LocalDate creationdate1 = LocalDate.of(2007,6,30);
        LocalDate birthdate = LocalDate.of(1950,03,13);
        LocalDate from = LocalDate.of(1980,03,13);
        LocalDate until = LocalDate.of(1990,05,15);
        Application newApplication = new Application("1234", "firstname", "lastname",birthdate, "birthplace", "city", "mainProfession", "jobTitle", "hfk2", "initiatior", "distinctionsRecieved", 12, 3, "result", "sanctions", State.GOEDKEURING_1,"scale",  "proposed", "report", "unknown", "unknown", "unknown", null, null, null, null, null, creationdate1, null, null, null, null, null, null, "distinction");
        Career career = new Career(null, "A4b1", from, until, 10.0, "naturePerformances", newApplication);

        ResponseEntity<Career> response = careerController.createCareer(career);

        assertEquals(null, response.getBody());
    }

    @Test
    public void testDeleteCareer_ReturnsOk() {
        int careerId = 50;
        Career career = new Career("testFunction", "A4b1", LocalDate.of(1980, 3, 13),
                LocalDate.of(1990, 5, 15), 10.0, "naturePerformances", null);
        career.setId(careerId);

        when(careerService.findById(careerId)).thenReturn(Optional.of(career));

        ResponseEntity<Career> response = careerController.deleteCareer(careerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(careerService, times(1)).deleteCareerById(careerId);
    }
    @Test
    public void testDeleteCareer_ReturnsNotOk() {
        LocalDate creationdate1 = LocalDate.of(2007,6,30);
        int careerId = 50;
        LocalDate birthdate = LocalDate.of(1950,03,13);
        LocalDate from = LocalDate.of(1980,03,13);
        LocalDate until = LocalDate.of(1990,05,15);
        Application newApplication = new Application("1234", "firstname", "lastname",birthdate, "birthplace", "city", "mainProfession", "jobTitle", "hfk2", "initiatior", "distinctionsRecieved", 12, 3, "result", "sanctions", State.GOEDKEURING_1,"scale",  "proposed", "report", "unknown", "unknown", "unknown", null, null, null, null, null, creationdate1, null, null, null, null, null, null, "distinction");
        Career career = new Career("testFunction", "A4b1", from, until, 10.0, "naturePerformances", newApplication);
        career.setId(50);
        ResponseEntity<Career> response = careerController.deleteCareer(200);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(careerService, times(0)).deleteCareerById(200);
    }
}