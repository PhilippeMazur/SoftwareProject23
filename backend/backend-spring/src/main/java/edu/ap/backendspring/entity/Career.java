package edu.ap.backendspring.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_career")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "function_title")
    private String functionTitle;

    @Column(name = "grade")

    private String grade;
    @Column(name = "from_date")

    private LocalDate fromDate;
    @Column(name = "to_date")

    private LocalDate toDate;
    @Column(name = "performance_breach")

    private double performanceBreach;
    @Column(name = "nature_performances")

    private String naturePerformances;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_application")
    private Application application;

    public Career() {
    }

    public Career(String functionTitle, String grade, LocalDate fromDate, LocalDate toDate, double performanceBreach, String naturePerformances, Application application) {
        this.functionTitle = functionTitle;
        this.grade = grade;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.performanceBreach = performanceBreach;
        this.naturePerformances = naturePerformances;
        this.application = application;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunctionTitle() {return functionTitle;}

    public void setFunctionTitle(String functionTitle) {this.functionTitle = functionTitle;}
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate from) {
        this.fromDate = from;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate to) {
        this.toDate = to;
    }

    public double getPerformanceBreach() {
        return performanceBreach;
    }

    public void setPerformanceBreach(double performanceBreach) {
        this.performanceBreach = performanceBreach;
    }

    public String getNaturePerformances() {
        return naturePerformances;
    }

    public void setNaturePerformances(String naturePerformances) {
        this.naturePerformances = naturePerformances;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Career{" +
                "id=" + id +
                ", functionTitle='" + functionTitle + '\'' + 
                ", grade='" + grade + '\'' +
                ", from=" + fromDate +
                ", to=" + toDate +
                ", performanceBreach='" + performanceBreach + '\'' +
                ", naturePerformances='" + naturePerformances + '\'' +
                ", application=" + application +
                '}';
    }
}
