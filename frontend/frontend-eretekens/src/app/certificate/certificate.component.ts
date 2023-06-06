import { Component, OnInit } from '@angular/core';
import { ICertificate } from '../entity/Certificate';
import { IApplication } from '../entity/Application';
import { ApplicationService } from '../service/application-service';
import { ActivatedRoute, Router } from '@angular/router';
import { CertificateService } from '../service/certificate-service';
import { throwError } from 'rxjs';
import { ApplicationState } from '../enum/application-state';

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.css']
})
export class CertificateComponent implements OnInit {
  
  newCertificate = {} as ICertificate;
  application = {} as IApplication;
  constructor(private applicationService: ApplicationService,private certificateService: CertificateService,private route: ActivatedRoute, private router:Router)  { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const applicationId = params['id'];
      this.applicationService.getApplicationById(applicationId).subscribe(
        (application: IApplication) => {
          this.application = application;
        },
        (error) => {
          console.error('Error retrieving application:', error);
          }
        );
      });
    };

    submitCertificate():void{
      alert("Certificaat opgeslagen!");
      this.certificateService.postCertificate(this.createCertificate()).subscribe(response =>
        {
          this.applicationService.updateApplication(this.application.id!, this.application)
          .subscribe(() => this.goBack())
        },
        error => throwError(error));
    }

    createCertificate():ICertificate{
      var newCertificate : ICertificate = {
        id:0,
        genderApplicant: this.newCertificate.genderApplicant,
        jobTitleOption : this.newCertificate.jobTitleOption,
        management : this.newCertificate.management,
        totYearService : this.application.totYearService!,
        application: {"id": this.application.id, "lastname":null, "distinctionsReceived":null,
        "jobTitle":null,"birthplace":null, "birthdate":null, "city":null, "gradeOrRank":null,"mainProfession":null,"initiator":null,
      "resultEvaluation":null,"nationalRegisterNr":null, state: ApplicationState.GOEDKEURING_1, "salaryScale": null,
    "totYearService":null, "totMonthService" : null,"sanctions":null, "reportAboutInvolved":null,"proposedHonoraryDistinction":null,"firstname":null,"decision":null,"decisionTranslated":null,"advice":null, "approvalABB":null,"approvalABBDate":null, "approvalMinister":null,"approvalMinisterDate":null,"approvalPrimeMinister":null,"approvalPrimeMinisterDate":null,"approvalChancellery":null,"approvalChancelleryDate":null,"approvalKing":null,"approvalKingDate":null,"dateCreated":null, "comment": null, "proposedHonoraryDistinctionTitle":null}
          }
          this.application.state = ApplicationState.KLAAR;
        return newCertificate;
    }
  goBack(){
    this.router.navigate(['overview']);
  }
}
    
