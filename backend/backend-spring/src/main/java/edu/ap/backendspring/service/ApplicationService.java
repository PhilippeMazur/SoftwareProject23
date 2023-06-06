package edu.ap.backendspring.service;

import edu.ap.backendspring.entity.Application;
import edu.ap.backendspring.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    public List<Application> findAllApplications(){
        return applicationRepository.findAll();
    }

    public Optional<Application> findApplicationById(int id) {
        return applicationRepository.findById(id);
    }

    public Application createApplication(Application application){
        if(application.getLastname() == null || application.getJobTitle() == null
        || application.getBirthdate() == null || application.getBirthplace() ==null
        || application.getCity() == null || application.getMainProfession() == null
        || application.getState() == null || application.getInitiator() == null
        || application.getFirstname() == null || application.getNationalRegisterNr() == null
        || application.getTotYearService() == 0 || application.getTotMonthService() == 0
        ||application.getGradeOrRank() == null){
            throw new IllegalArgumentException("Cannot create the application. Please provide values for the required fields.");
        }
        return applicationRepository.save(application);
    }

    public Application updateApplication(int id, Application application){
        Optional<Application> existingApplication = applicationRepository.findById(id);
        if(existingApplication.isEmpty())
            throw new IllegalArgumentException("Application doesn't exist");

        Application updateApplication = existingApplication.get();
        updateApplication.setNationalRegisterNr(application.getNationalRegisterNr());
        updateApplication.setFirstname(application.getFirstname());
        updateApplication.setLastname(application.getLastname());
        updateApplication.setBirthdate(application.getBirthdate());
        updateApplication.setBirthplace(application.getBirthplace());
        updateApplication.setCity(application.getCity());
        updateApplication.setMainProfession(application.getMainProfession());
        updateApplication.setJobTitle(application.getJobTitle());
        updateApplication.setGradeOrRank(application.getGradeOrRank());
        updateApplication.setInitiator(application.getInitiator());
        updateApplication.setDistinctionsReceived(application.getDistinctionsReceived());
        updateApplication.setTotYearService(application.getTotYearService());
        updateApplication.setTotMonthService(application.getTotMonthService());
        updateApplication.setResultEvaluation(application.getResultEvaluation());
        updateApplication.setSanctions(application.getSanctions());
        updateApplication.setState(application.getState());
        updateApplication.setSalaryScale(application.getSalaryScale());
        updateApplication.setProposedHonoraryDistinction(application.getProposedHonoraryDistinction());
        updateApplication.setReportAboutInvolved(application.getReportAboutInvolved());
        updateApplication.setDecision(application.getDecision());
        updateApplication.setDecisionTranslated(application.getDecisionTranslated());
        updateApplication.setAdvice(application.getAdvice());
        updateApplication.setApprovalABB(application.getApprovalABB());
        updateApplication.setApprovalMinister(application.getApprovalMinister());
        updateApplication.setApprovalPrimeMinister(application.getApprovalPrimeMinister());
        updateApplication.setApprovalChancellery(application.getApprovalChancellery());
        updateApplication.setApprovalKing(application.getApprovalKing());
        updateApplication.setDateCreated(application.getDateCreated());
        updateApplication.setApprovalABBDate(application.getApprovalABBDate());
        updateApplication.setApprovalMinisterDate(application.getApprovalMinisterDate());
        updateApplication.setApprovalPrimeMinisterDate(application.getApprovalPrimeMinisterDate());
        updateApplication.setApprovalChancelleryDate(application.getApprovalChancelleryDate());
        updateApplication.setApprovalKingDate(application.getApprovalKingDate());
        updateApplication.setComment(application.getComment());
        return applicationRepository.save(updateApplication);
    }

    public Optional<Application> deleteApplicationById(int id){
        Optional<Application> existingApplication = applicationRepository.findById(id);
        if(existingApplication.isEmpty())
            throw new IllegalArgumentException("Application doesn't exist");
         applicationRepository.deleteById(id);
         return existingApplication;
    }

}