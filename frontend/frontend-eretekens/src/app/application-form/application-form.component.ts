import { Component, OnInit, ElementRef, ViewChild, Type} from '@angular/core';
import { Router } from '@angular/router';
import { IApplication } from '../entity/Application';
import { ICareer } from '../entity/Career';
import { ActivatedRoute } from '@angular/router';
import { IABBCareer, ICareerItem } from '../entity/ABBCareer';
import { ApplicationService } from '../service/application-service';
import { ABBService } from '../service/abb.service';
import { ApplicationState } from '../enum/application-state';

@Component({
  selector: 'app-applicationForm',
  templateUrl: './application-form.component.html',
  styleUrls: ['./application-form.component.css']
})
export class ApplicationFormComponent implements OnInit {
  careerData: IABBCareer | undefined;
  careerItems: ICareerItem[] | undefined;

  newApplication = {} as IApplication;
  newCareer = {} as ICareer;
  currentPeriod : Date = new Date();
  functionTitle: string[] = [];
  grade: string[] = [];
  from: Date[]= [];
  to: Date[]= [];
  performanceBreach: number[]= [];
  naturePerformances: string[]= [];
  age: number = 0;
  serviceMonths: number;
  savedId : number | undefined = -1;

  invalidMonth : boolean = false;
  validGradeOrRank : boolean = true;
  validGrades : boolean = true;

  mandataris: boolean = false;

  regex = /^[0-9!@#\$%\^\&*\)\(+=._-]+$/;


@ViewChild("myPopup") private parentRef: ElementRef<HTMLElement>;
parentCompleted : HTMLElement;
@ViewChild("sendButton",{ static: true }) private sendButton: ElementRef;
public applications: IApplication[];

  constructor(private applicationService: ApplicationService, private router : Router, private route: ActivatedRoute, private ABBService: ABBService) {}

  ngOnInit(){

    setTimeout(() => {

      this.applicationService.getAllApplications().subscribe(a => this.applications = a);

      this.newApplication.nationalRegisterNr = this.route.snapshot.paramMap.get('rrn')!;

      this.ABBService.getPersonByNationalNumber(this.newApplication.nationalRegisterNr).subscribe(p => {
        this.newApplication.firstname = p.firstName!;
        this.newApplication.lastname = p.lastName!;
        this.newApplication.birthplace = p.placeOfBirth!;
        this.newApplication.birthdate = p.dateOfBirth!;
        this.newApplication.city = p.city!;
      });

      this.getCareerByNationalNumber(this.newApplication.nationalRegisterNr);
    }, 1000);
  };

public getCareerByNationalNumber(rrn: string) {
    this.ABBService.getCareerByNationalNumber(rrn).subscribe(
      (data: IABBCareer) => {
        this.careerData = data;
        this.careerItems = this.getCareerPath();
        this.populateArrays();
        this.calculateServiceTime(this.from,this.to);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

public populateArrays() {
  if(this.careerItems) {
    for (let i = 0; i < this.careerItems.length; i++) {
      this.functionTitle[i] = this.careerItems[i].functionTitle;
      this.grade[i] = this.careerItems[i].grade;
      this.from[i] = this.careerItems[i].from!;
      this.to[i] = this.careerItems[i].until!;
      this.performanceBreach[i] = this.careerItems[i].percentage;
      this.naturePerformances[i] = this.careerItems[i].statute;
      }

      this.newApplication.mainProfession = this.careerData?.currentOccupation!;
      this.newApplication.jobTitle = this.careerData?.currentFunctionTitle!;
      this.newApplication.gradeOrRank = this.careerData?.currentGrade!;
      this.newApplication.salaryScale = this.careerData?.currentSalary!;
    }
  }

public getCareerPath(): ICareerItem[]{

    if (this.careerData && this.careerData.career) {
      return this.careerData.career;
    }
    return [];
  }


parseDate(dateString: string): Date {
  if (dateString) {
      return new Date(dateString);
  }
  return new Date();
}

public createBadgeOfHonour(){
  this.currentPeriod = new Date();
  this.applicationService.postAanvragen(this.createNewApplication())
  .subscribe(
    response => {
      const parseResponse = response as IApplication;
      this.savedId = parseResponse.id;
      this.createCareer();
      this.openPopUp();
    },
    error => console.error(error)
  );

  }

public createNewApplication(): IApplication{

    let application : IApplication  = {
    firstname: this.newApplication.firstname,
    lastname: this.newApplication.lastname,
    nationalRegisterNr: this.newApplication.nationalRegisterNr,
    birthplace: this.newApplication.birthplace,
    birthdate: this.newApplication.birthdate,
    city: this.newApplication.city,
    mainProfession: this.newApplication.mainProfession,
    jobTitle: this.newApplication.jobTitle,
    gradeOrRank: this.newApplication.gradeOrRank,
    initiator: this.newApplication.initiator,
    distinctionsReceived: this.newApplication.distinctionsReceived,
    totYearService: this.newApplication.totYearService,
    totMonthService: this.newApplication.totMonthService,
    dateCreated: this.currentPeriod,
    resultEvaluation: this.newApplication.resultEvaluation,
    sanctions: this.newApplication.sanctions,
    salaryScale: this.newApplication.salaryScale,
    state: ApplicationState.GOEDKEURING_1,
    proposedHonoraryDistinction: this.newApplication.proposedHonoraryDistinction,
    reportAboutInvolved : this.newApplication.reportAboutInvolved,
    decision: null,
    decisionTranslated: null,
    advice: null,
    approvalABB: null,
    approvalABBDate: null,
    approvalMinister: null,
    approvalMinisterDate: null,
    approvalPrimeMinister: null,
    approvalPrimeMinisterDate: null,
    approvalChancellery: null,
    approvalChancelleryDate: null,
    approvalKing: null,
    approvalKingDate: null,

    comment: null,
    proposedHonoraryDistinctionTitle: this.newApplication.proposedHonoraryDistinctionTitle
  }
  return application;
}

public createCareer (): void {
  for(let i = 0 ; i <8; i++){
    var career: ICareer = {
      id: 0,
      functionTitle: this.functionTitle[i],
      grade: this.grade[i],
      fromDate : this.from[i],
      toDate : this.to[i],
      performanceBreach : this.performanceBreach[i],
      naturePerformances : this.naturePerformances[i],
      application: {"id": this.savedId, "lastname":null, "distinctionsReceived":null,
    "jobTitle":null,"birthplace":null, "birthdate":null, "city":null, "gradeOrRank":null,"mainProfession":null,"initiator":null,
  "resultEvaluation":null,"nationalRegisterNr":null, state: ApplicationState.GOEDKEURING_1, "salaryScale": null,
"totYearService":null, "totMonthService" : null,"sanctions":null, "reportAboutInvolved":null,"proposedHonoraryDistinction":null,"firstname":null,"decision":null,"decisionTranslated":null,"advice":null, "approvalABB":null,"approvalABBDate":null, "approvalMinister":null,"approvalMinisterDate":null,"approvalPrimeMinister":null,"approvalPrimeMinisterDate":null,"approvalChancellery":null,"approvalChancelleryDate":null,"approvalKing":null,"approvalKingDate":null,"dateCreated":null, "comment":null, "proposedHonoraryDistinctionTitle": null }

} ;


if(this.grade[i] != undefined || this.from[i] != undefined || this.to[i] != undefined
  || this.performanceBreach[i] != undefined  || this.naturePerformances[i] != undefined)
   { this.applicationService.postCareer(career)
    .subscribe(
      response =>{
        console.log(response)},
        error => console.log(error));
      }
    }
}
public openPopUp():void{
  this.getParent();
-  this.sendButton.nativeElement.classList.add("hiddenSendButton")
  this.parentCompleted.classList.add("open-popup");

}
public closePopUp():void{
  this.getParent();
  this.parentCompleted.classList.remove("open-popup");
 this.sendButton.nativeElement.classList.remove("hiddenSendButton")
 setTimeout(() => {
  this.router.navigate(["/overview"])
}, 10);

}
public getParent() {
  this.parentCompleted = this.parentRef.nativeElement;
}

trackByFn(index: any, item: any) {
  return index;
}

validateTextInput(value : string | null):boolean{
  if(value != null){
  return /\d/.test(value)
  }
  else return false;
}
isNumberOrSpecialCharatersOnly(value: string): boolean {
  return this.regex.test(value) && !this.valueIsEmpty(value);
}
validateGradeOrRank() : void{
  if(!this.valueIsEmpty(this.newApplication.gradeOrRank!))
 this.validGradeOrRank =  this.newApplication.gradeOrRank!.length >= 2;
  else this.validGradeOrRank = true;
}
validateGradesAsNumbers(): void {
  this.validGrades =  true;
  for (const grade of this.grade) {
    if (this.isNumberOrSpecialCharatersOnly(grade)) {
      this.validGrades =  false;
    }
  }
}

validateServiceMonths(): void {
  this.invalidMonth =  false;
  if(this.newApplication.totMonthService! > 11 || this.newApplication.totMonthService! < 0){
    this.invalidMonth = true;
  }
}

calculateServiceTime(from:Date[],to:Date[]): void {
  let totMonthService = 0;
  let totYearService = 0;

  for (let i = 0; i < this.naturePerformances.length; i++) {
    if (this.mandataris == false)
    {
      if (this.naturePerformances[i] == "vastbenoemd") {
        const startDate = new Date (from[i]);
        var endDate = new Date (to[i]);

        const years = endDate.getFullYear() - startDate.getFullYear();
        const months = years * 12 + (endDate.getMonth() - startDate.getMonth());
  
        totMonthService += months % 12;
        totYearService += Math.floor(months / 12);
      }
    }
    else if (this.mandataris == true)
    {
      if (this.naturePerformances[i] == "mandataris") {
        const startDate = new Date (from[i]);
        var endDate = new Date (to[i]);
        
        const years = endDate.getFullYear() - startDate.getFullYear();
        const months = years * 12 + (endDate.getMonth() - startDate.getMonth());
  
        totMonthService += months % 12;
        totYearService += Math.floor(months / 12);
      }
    }
  }

  if (totMonthService > 11) {
    totYearService += Math.floor(totMonthService / 12);
    totMonthService = totMonthService % 12;
  }

  this.newApplication.totMonthService = totMonthService;
  this.newApplication.totYearService = totYearService;

  if(totYearService < 0)
  {
    alert("Geef geldige datums in")
  }
}

valueIsEmpty(value:string){
  return value.trim() == "";
}

decideDistinctionBurgerlijk(){
  if(this.newApplication.totYearService == null)
  {
    this.newApplication.totYearService = 0;  
  }
  if(this.newApplication.city == null)
  {
    this.newApplication.city = "";
  }

  if(this.newApplication.proposedHonoraryDistinction?.includes("ereteken") )
  {
    for(let i = 0; i < this.grade.length; i++){
      if(!this.mandataris)
      {
        //Toekenning burgerlijke eretekens aan uitvoerende leden van Kerkfabrieken en eredienstinstellingen
        //Toekenning burgerlijke eretekens personeel gemeenten, OCMW’s en provincies
        if(this.grade[i] == "1.C" || this.grade[i] == "1.B" || this.grade[i] == "1.A" || this.grade[i] == "2.+B" || this.grade[i] == "2.+A" || this.grade[i] == "2.B" || this.grade[i] == "2.A")
        {
          if(this.newApplication.totYearService >= 25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijke Medaille Eerste Klasse"
          }
          if(this.newApplication.totYearService >= 35)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijk Kruis Eerste Klasse"
          }
          if(this.newApplication.totYearService <25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Onvoldoende dienstanciënniteit gemeenten, OCMW’s en provincies";
          }
        }
        if(this.grade[i] == "3")
        {
          if(this.newApplication.totYearService >= 25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijke Medaille Eerste Klasse"
          }
          if(this.newApplication.totYearService >= 35)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijk Kruis Tweede Klasse"
          }
          if(this.newApplication.totYearService <25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Onvoldoende dienstanciënniteit gemeenten, OCMW’s en provincies";
          }
        }
        if(this.grade[i] == "4.B")
        {
          if(this.newApplication.totYearService >= 25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijke Medaille Tweede Klasse"
          }
          if(this.newApplication.totYearService >= 35)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijkz Medaille Eerste Klasse"
          }
          if(this.newApplication.totYearService <25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Onvoldoende dienstanciënniteit gemeenten, OCMW’s en provincies";
          }
        }
        if(this.grade[i] == "4.A")
        {
          if(this.newApplication.totYearService >= 25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijke Medaille Derde Klasse"
          }
          if(this.newApplication.totYearService >= 35)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijke Medaille Tweede Klasse"
          }
          if(this.newApplication.totYearService <25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Onvoldoende dienstanciënniteit gemeenten, OCMW’s en provincies";
          }
        }
        }
        else if(this.mandataris)
        {
          if(this.newApplication.totYearService >= 25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijke Medaille Eerste Klasse"
          }
          if(this.newApplication.totYearService >= 35)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Burgerlijk kruis Eerste Klasse"
          }
          if(this.newApplication.totYearService <25)
          {
            this.newApplication.proposedHonoraryDistinctionTitle = "Onvoldoende dienstanciënniteit mandataris";
          }
        }
    }
  }
  else
  {
    this.newApplication.proposedHonoraryDistinctionTitle = "geen burgerlijk ereteken";
  }
}

goBack(){
  this.router.navigate(['pre-application']);
}
}


