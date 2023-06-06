package edu.ap.backendspring.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.entity.Career;
import edu.ap.backendspring.repository.ApplicationRepository;
import edu.ap.backendspring.repository.CareerRepository;

@RunWith(SpringRunner.class)
public class CareerServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private CareerRepository careerRepository;

    private CareerService careerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        careerService = new CareerService();
        careerService.applicationRepository = applicationRepository;
        careerService.careerRepository = careerRepository;
    }

    @Test
    public void testFindAllCareers() {
        List<Career> expected = Collections.singletonList(Mockito.mock(Career.class));
        Mockito.when(careerRepository.findAll()).thenReturn(expected);

        Iterable<Career> actual = careerService.findAllCareers();

        assertEquals(expected, actual);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Optional<Career> expected = Optional.of(Mockito.mock(Career.class));
        Mockito.when(careerRepository.findById(id)).thenReturn(expected);

        Optional<Career> actual = careerService.findById(id);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllCareersByApplicationId() {
        Integer id = 1;
        Application application = new Application();
        application.setId(id);
        Career career = new Career();
        career.setApplication(application);
        List<Career> expected = Collections.singletonList(career);
        Mockito.when(careerRepository.findAll()).thenReturn(expected);

        Iterable<Career> actual = careerService.findAllCareersByApplicationId(id);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCareerWithMissingFields() {
        Career career = new Career();
        careerService.createCareer(career);
    }

    @Test
    public void testCreateCareer() {
        LocalDate to = LocalDate.of(2022,2,1); // today's date
        LocalDate from = LocalDate.of(2022, 1, 1); // January 1, 2022

        Career expected = new Career();
        expected.setFunctionTitle("Programmer1");
        expected.setToDate(to);
        expected.setFromDate(from);
        expected.setNaturePerformances("Programmer");
        expected.setGrade("AS1");
        expected.setPerformanceBreach(50);

        Mockito.when(careerRepository.save(Mockito.any(Career.class))).thenReturn(expected);

        Career actual = careerService.createCareer(expected);

        assertEquals(expected, actual);
    }
}