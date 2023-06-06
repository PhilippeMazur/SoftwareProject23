package edu.ap.backendspring.service;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.entity.Career;
import edu.ap.backendspring.repository.ApplicationRepository;
import edu.ap.backendspring.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CareerService {

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    CareerRepository careerRepository;

    public List<Career> findAllCareers(){
        return careerRepository.findAll();
    }

    public Optional<Career> findById(@PathVariable("id") Integer id){
        return careerRepository.findById(id);
    }

    public List<Career> findAllCareersByApplicationId(@PathVariable("id") Integer id){
        return StreamSupport.stream(this.findAllCareers().spliterator(), false)
                        .filter((career -> career.getApplication().getId() == id))
                        .collect(Collectors.toList());
    }
    public Career createCareer(Career career){
        if(career.getFunctionTitle() ==null || career.getToDate() == null
        ||career.getNaturePerformances() == null || career.getFromDate() == null
        || career.getGrade() == null || career.getPerformanceBreach() == 0)
            throw new IllegalArgumentException("Cannot create the career. Please provide values for the required fields.");
        
        return careerRepository.save(career);
    }
    public Optional<Career> deleteCareerById(int id){
        Optional<Career> existingCareer = careerRepository.findById(id);
        if(existingCareer.isEmpty())
            throw new IllegalArgumentException("Application doesn't exist");
        careerRepository.deleteById(id);
        return existingCareer;
    }

}
