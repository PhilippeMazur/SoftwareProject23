import { Component, OnInit } from '@angular/core';
import { IApplication } from '../entity/Application';
import { ICareer } from '../entity/Career';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { ApplicationService } from '../service/application-service';
import { ICertificate } from '../entity/Certificate';
import { CertificateService } from '../service/certificate-service';

@Component({
  selector: 'app-view-full',
  templateUrl: './view-full.component.html',
  styleUrls: ['./view-full.component.css']
})
export class ViewFullComponent implements OnInit {
  applications: IApplication[] = [];
  applicationToShow?: IApplication;
  applicationLoopbaan?: ICareer[];
  applicationID:number = 0;
  certificate = {} as ICertificate;
  previousPage: String = "";

  constructor(public dbService :ApplicationService, private route:ActivatedRoute, private router:Router, private certificateService :CertificateService) {
    this.dbService.getAllApplications().subscribe(data => this.applications = data);
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.applicationID = params['id'];
      this.previousPage = params['prev'];
      });

    this.dbService.getApplicationById(this.applicationID).subscribe(data => {
      this.applicationToShow = data;
    });
    this.dbService.getCareersByApplicationId(this.applicationID).subscribe(a => {
      this.applicationLoopbaan = a;
    })
    this.certificateService.getCertficateByApplicationId(this.applicationID).subscribe(data => {
      this.certificate = data;
    })



    }
    

    goBack() {
      this.router.navigate([this.previousPage]);
    }
  }
