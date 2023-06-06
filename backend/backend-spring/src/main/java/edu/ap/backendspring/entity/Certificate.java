package edu.ap.backendspring.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "gender_applicant")
    private String genderApplicant;

    @Column(name = "job_title_option")
    private String jobTitleOption;

    @Column(name = "management")
    private String management;

    @Column(name = "tot_year_service")
    private int totYearService;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "certificate_application_id")
    private Application application;

    public Certificate() {
    }

    public Certificate(String genderApplicant, String jobTitleOption, String management, int totYearService, Application application) {
        this.genderApplicant = genderApplicant;
        this.jobTitleOption = jobTitleOption;
        this.management = management;
        this.totYearService = totYearService;
        this.application = application;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenderApplicant() {
        return genderApplicant;
    }

    public void setGenderApplicant(String genderApplicant) {
        this.genderApplicant = genderApplicant;
    }

    public String getJobTitleOption() {
        return jobTitleOption;
    }

    public void setJobTitleOption(String option) {
        this.jobTitleOption = option;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public int getTotYearService() {
        return totYearService;
    }

    public void setTotYearService(int totYearService) {
        this.totYearService = totYearService;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", genderApplicant='" + genderApplicant + '\'' +
                ", jobTitleOption='" + jobTitleOption + '\'' +
                ", management='" + management + '\'' +
                ", totYearService=" + totYearService +
                ", application=" + application +
                '}';
    }
}
