package edu.ap.backendspring.entity;

import edu.ap.backendspring.enums.State;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tbl_application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name = "national_register_nr")
    private String nationalRegisterNr;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "birthplace")
    private String birthplace;

    @Column(name = "city")
    private String city;

    @Column(name = "main_profession")
    private String mainProfession;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "grade_or_rank")
    private String gradeOrRank;

    @Column(name = "initiator")
    private String Initiator;
    @Column(name = "distinctions_received")
    private String distinctionsReceived;
    @Column(name = "tot_year_service")
    private int totYearService;
    @Column(name = "tot_month_service")
    private int totMonthService;
    @Column(name = "result_evaluation")
    private String resultEvaluation;
    @Column(name = "sanctions")
    private String sanctions;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "salary_scale")
    private String salaryScale;
    @Column(name = "proposed_honorary_distinction")
    private String proposedHonoraryDistinction;
    @Column(name = "report_about_involved")
    private String reportAboutInvolved;
    @Column(name = "decision")
    private String decision;
    @Column(name = "decision_translated")
    private String decisionTranslated;
    @Column(name = "advice")
    private String advice;
    @Column(name = "approval_ABB")
    private String approvalABB;
    @Column(name = "approval_minister")
    private String approvalMinister;
    @Column(name = "approval_prime_minister")
    private String approvalPrimeMinister;
    @Column(name = "approval_chancellery")
    private String approvalChancellery;
    @Column(name = "approval_king")
    private String approvalKing;
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "approval_ABB_date")
    private LocalDate approvalABBDate;

    @Column(name = "approval_minister_date")
    private LocalDate approvalMinisterDate;

    @Column(name = "approval_prime_minister_date")
    private LocalDate approvalPrimeMinisterDate;

    @Column(name = "approval_chancellery_date")
    private LocalDate approvalChancelleryDate;

    @Column(name = "approval_king_date")
    private LocalDate approvalKingDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "proposed_honorary_distinction_title")
    private String proposedHonoraryDistinctionTitle;

    public Application() {

    }

    public Application(String nationalRegisterNr, String firstname, String lastname, LocalDate birthdate, String birthplace, String city, String mainProfession, String jobTitle, String gradeOrRank, String initiator, String distinctionsReceived, int totYearService, int totMonthService, String resultEvaluation, String sanctions, State state, String salaryScale, String proposedHonoraryDistinction, String reportAboutInvolved, String decision, String decisionTranslated, String advice, String approval_ABB, String approval_minister, String approval_prime_minister, String approval_chancellery, String approval_king, LocalDate dateCreated, LocalDate approval_ABB_date, LocalDate approval_minister_date, LocalDate approval_prime_minister_date, LocalDate approval_chancellery_date, LocalDate approval_king_date, String comment, String proposedHonoraryDistinctionTitle) {
        this.nationalRegisterNr = nationalRegisterNr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.city = city;
        this.mainProfession = mainProfession;
        this.jobTitle = jobTitle;
        this.gradeOrRank = gradeOrRank;
        Initiator = initiator;
        this.distinctionsReceived = distinctionsReceived;
        this.totYearService = totYearService;
        this.totMonthService = totMonthService;
        this.resultEvaluation = resultEvaluation;
        this.sanctions = sanctions;
        this.state = state;
        this.salaryScale = salaryScale;
        this.proposedHonoraryDistinction = proposedHonoraryDistinction;
        this.reportAboutInvolved = reportAboutInvolved;
        this.decision = decision;
        this.decisionTranslated = decisionTranslated;
        this.advice = advice;
        this.approvalABB = approval_ABB;
        this.approvalMinister = approval_minister;
        this.approvalPrimeMinister = approval_prime_minister;
        this.approvalChancellery = approval_chancellery;
        this.approvalKing = approval_king;
        this.dateCreated = dateCreated;
        this.approvalABBDate = approval_ABB_date;
        this.approvalMinisterDate = approval_minister_date;
        this.approvalPrimeMinisterDate = approval_prime_minister_date;
        this.approvalChancelleryDate = approval_chancellery_date;
        this.approvalKingDate = approval_king_date;
        this.comment = comment;
        this.proposedHonoraryDistinctionTitle = proposedHonoraryDistinctionTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationalRegisterNr() {
        return nationalRegisterNr;
    }

    public void setNationalRegisterNr(String nationalRegisterNr) {
        this.nationalRegisterNr = nationalRegisterNr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }
    public String getMainProfession() {
        return mainProfession;
    }

    public void setMainProfession(String mainProfession) {
        this.mainProfession = mainProfession;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getGradeOrRank() {
        return gradeOrRank;
    }

    public void setGradeOrRank(String gradeOrRank) {
        this.gradeOrRank = gradeOrRank;
    }

    public String getInitiator() {
        return Initiator;
    }

    public void setInitiator(String initiator) {
        Initiator = initiator;
    }

    public String getDistinctionsReceived() {
        return distinctionsReceived;
    }

    public void setDistinctionsReceived(String distinctionsReceived) {
        this.distinctionsReceived = distinctionsReceived;
    }

    public int getTotYearService() {
        return totYearService;
    }

    public void setTotYearService(int totYearService) {
        this.totYearService = totYearService;
    }

    public int getTotMonthService() {
        return totMonthService;
    }

    public void setTotMonthService(int totMonthService) {
        this.totMonthService = totMonthService;
    }

    public String getResultEvaluation() {
        return resultEvaluation;
    }

    public void setResultEvaluation(String resultEvaluation) {
        this.resultEvaluation = resultEvaluation;
    }

    public String getSanctions() {
        return sanctions;
    }

    public void setSanctions(String sanctions) {
        this.sanctions = sanctions;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getSalaryScale() {
        return salaryScale;
    }

    public void setSalaryScale(String salaryScale) {
        this.salaryScale = salaryScale;
    }

    public String getProposedHonoraryDistinction() {
        return proposedHonoraryDistinction;
    }

    public void setProposedHonoraryDistinction(String proposedHonoraryDistinction) {
        this.proposedHonoraryDistinction = proposedHonoraryDistinction;
    }

    public String getReportAboutInvolved() {
        return reportAboutInvolved;
    }

    public void setReportAboutInvolved(String reportAboutInvolved) {
        this.reportAboutInvolved = reportAboutInvolved;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getDecisionTranslated() {
        return decisionTranslated;
    }

    public void setDecisionTranslated(String decisionTranslated) {
        this.decisionTranslated = decisionTranslated;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getApprovalABB() {
        return approvalABB;
    }

    public void setApprovalABB(String approval_ABB) {
        this.approvalABB = approval_ABB;
    }

    public String getApprovalMinister() {
        return approvalMinister;
    }

    public void setApprovalMinister(String approval_minister) {
        this.approvalMinister = approval_minister;
    }

    public String getApprovalPrimeMinister() {
        return approvalPrimeMinister;
    }

    public void setApprovalPrimeMinister(String approval_prime_minister) {
        this.approvalPrimeMinister = approval_prime_minister;
    }

    public String getApprovalChancellery() {
        return approvalChancellery;
    }

    public void setApprovalChancellery(String approval_chancellery) {
        this.approvalChancellery = approval_chancellery;
    }

    public String getApprovalKing() {
        return approvalKing;
    }

    public void setApprovalKing(String approval_king) {
        this.approvalKing = approval_king;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getApprovalABBDate() {
        return approvalABBDate;
    }

    public void setApprovalABBDate(LocalDate approval_ABB_date) {
        this.approvalABBDate = approval_ABB_date;
    }

    public LocalDate getApprovalMinisterDate() {
        return approvalMinisterDate;
    }

    public void setApprovalMinisterDate(LocalDate approval_minister_date) {
        this.approvalMinisterDate = approval_minister_date;
    }

    public LocalDate getApprovalPrimeMinisterDate() {
        return approvalPrimeMinisterDate;
    }

    public void setApprovalPrimeMinisterDate(LocalDate approval_prime_minister_date) {
        this.approvalPrimeMinisterDate = approval_prime_minister_date;
    }

    public LocalDate getApprovalChancelleryDate() {
        return approvalChancelleryDate;
    }

    public void setApprovalChancelleryDate(LocalDate approval_chancellery_date) {
        this.approvalChancelleryDate = approval_chancellery_date;
    }

    public LocalDate getApprovalKingDate() {
        return approvalKingDate;
    }

    public void setApprovalKingDate(LocalDate approval_king_date) {
        this.approvalKingDate = approval_king_date;
    }

    public String getComment() { return this.comment; }

    public void setComment(String comment) { this.comment = comment; }

    public String getProposedHonoraryDistinctionTitle() {return this.proposedHonoraryDistinctionTitle;}

    public void setProposedHonoraryDistinctionTitle(String proposedHonoraryDistinctionTitle) {this.proposedHonoraryDistinctionTitle = proposedHonoraryDistinctionTitle;}
    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", nationalRegisterNr='" + nationalRegisterNr + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", birthplace='" + birthplace + '\'' +
                ", city='" + city + '\'' +
                ", mainProfession='" + mainProfession + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", gradeOrRank='" + gradeOrRank + '\'' +
                ", Initiator='" + Initiator + '\'' +
                ", distinctionsReceived='" + distinctionsReceived + '\'' +
                ", totYearService=" + totYearService +
                ", totMonthService=" + totMonthService +
                ", resultEvaluation='" + resultEvaluation + '\'' +
                ", sanctions='" + sanctions + '\'' +
                ", state=" + state +
                ", salaryScale='" + salaryScale + '\'' +
                ", proposedHonoraryDistinction='" + proposedHonoraryDistinction + '\'' +
                ", reportAboutInvolved='" + reportAboutInvolved + '\'' +
                ", decision='" + decision + '\'' +
                ", decisionTranslated='" + decisionTranslated + '\'' +
                ", advice='" + advice + '\'' +
                ", approval_ABB='" + approvalABB + '\'' +
                ", approval_minister='" + approvalMinister + '\'' +
                ", approval_prime_minister='" + approvalPrimeMinister + '\'' +
                ", approval_chancellery='" + approvalChancellery + '\'' +
                ", approval_king='" + approvalKing + '\'' +
                ", dateCreated=" + dateCreated +
                ", approval_ABB_date=" + approvalABBDate +
                ", approval_minister_date=" + approvalMinisterDate +
                ", approval_prime_minister_date=" + approvalPrimeMinisterDate +
                ", approval_chancellery_date=" + approvalChancelleryDate +
                ", approval_king_date=" + approvalKingDate +
                ", comment=" + comment +
                ", proposedHonoraryDistinctionTitle=" + proposedHonoraryDistinctionTitle +
                '}';
    }
}
