import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { IApplication } from '../entity/Application';
import { ICareer } from '../entity/Career';
import { ApplicationService } from '../service/application-service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})

export class ViewComponent implements OnInit, OnDestroy {
  applications: IApplication[] = [];
  applicationToShow?: IApplication;
  applicationLoopbaan: ICareer[];
  destroy$: Subject<boolean> = new Subject<boolean>();
  applicationID:number = 0;


  constructor(public dbService :ApplicationService, private route:ActivatedRoute, private router:Router) { 
    this.dbService.getAllApplications().subscribe(data => this.applications = data);
    
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
    this.applicationID = this.route.snapshot.params['id'];
    this.dbService.getApplicationById(this.applicationID).subscribe(data => {
      this.applicationToShow = data;
    });

    this.dbService.getCareersByApplicationId(this.applicationID).subscribe(a => {
      this.applicationLoopbaan = a;
    })
    

    }
    
    goBack(){
      this.router.navigate(['overview']);
    }
  }
