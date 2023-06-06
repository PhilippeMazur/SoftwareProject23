import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { IApplication } from '../entity/Application'
import { ICareer } from '../entity/Career';
import { ApplicationService } from '../service/application-service';
import { ApplicationState } from '../enum/application-state';


@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})

export class ReviewComponent implements OnInit, OnDestroy {
  applications: IApplication[] = [];
  applicationToShow!: IApplication;
  applicationLoopbaan!: ICareer;
  destroy$: Subject<boolean> = new Subject<boolean>();
  applicationID: number = 0;
  approve: boolean = false;
  reject: boolean = false;

  constructor(public dbService :ApplicationService, private route:ActivatedRoute, private router:Router) { 
    this.dbService.getAllApplications().subscribe(data => this.applications = data);
    this.applicationID = this.route.snapshot.params['id'];


    this.dbService.getApplicationById(this.applicationID).subscribe(data => {
      this.applicationToShow = data;
    });
  }

  acceptApplication(approval:boolean){
    if(!approval){this.denyApplication()}
    else{
    alert("Aanvraag succesvol goedgekeurd");

    switch (this.applicationToShow.state) {
      case "GOEDKEURING_1":
        this.applicationToShow.state = ApplicationState.ADVIES;
        this.applicationToShow.approvalABBDate = new Date();

        if(this.approve) {
          this.applicationToShow.approvalABB = "true"
        } else {
          this.applicationToShow.approvalABB = "false"
        }
        this.dbService.updateApplication(this.applicationID,this.applicationToShow).subscribe(() => this.goBack());        break;
      case "GOEDKEURING_2":
        this.applicationToShow.state = ApplicationState.GOEDKEURING_3;
        this.applicationToShow.approvalMinisterDate = new Date();

        if(this.approve) {
          this.applicationToShow.approvalMinister = "true"
        } else {
          this.applicationToShow.approvalMinister = "false"
        }        
        this.dbService.updateApplication(this.applicationID,this.applicationToShow).subscribe(() => this.goBack());        break; 
      case "GOEDKEURING_3":
        this.applicationToShow.state = ApplicationState.GOEDKEURING_4;
        this.applicationToShow.approvalPrimeMinisterDate = new Date();

        if(this.approve) {
          this.applicationToShow.approvalPrimeMinister = "true"
        } else {
          this.applicationToShow.approvalPrimeMinister = "false"
        }        
        this.dbService.updateApplication(this.applicationID,this.applicationToShow).subscribe(() => this.goBack());        break; 
      case "GOEDKEURING_4":
        this.applicationToShow.state = ApplicationState.GOEDKEURING_5;
        this.applicationToShow.approvalChancelleryDate = new Date();

        if(this.approve) {
          this.applicationToShow.approvalChancellery = "true"
        } else {
          this.applicationToShow.approvalChancellery = "false"
        }        
        this.dbService.updateApplication(this.applicationID,this.applicationToShow).subscribe(() => this.goBack());        break; 
      case "GOEDKEURING_5":
        this.applicationToShow.state = ApplicationState.BESLUIT;
        this.applicationToShow.approvalKingDate = new Date();

        if(this.approve) {
          this.applicationToShow.approvalKing = "true"
        } else {
          this.applicationToShow.approvalKing = "false"
        }        
        this.dbService.updateApplication(this.applicationID,this.applicationToShow).subscribe(() => this.goBack());        break; 
    }
  }
  }
  
  denyApplication(){
    alert("Aanvraag succesvol afgekeurd");
    this.applicationToShow.state = ApplicationState.AFGEKEURD;
    this.dbService.updateApplication(this.applicationID,this.applicationToShow)
    .subscribe(() => this.goBack());
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
    
    }

    goBack(){
      this.router.navigate(['overview']);
    }
  }

