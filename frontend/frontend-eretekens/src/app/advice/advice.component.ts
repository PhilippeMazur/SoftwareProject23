import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterEvent, RouterLink, RouterOutlet } from '@angular/router';
import { IApplication } from '../entity/Application';
import { ApplicationService } from '../service/application-service';
import { ApplicationState } from '../enum/application-state';

@Component({
  selector: 'app-advice',
  templateUrl: './advice.component.html',
  styleUrls: ['./advice.component.css']
})
export class AdviceComponent implements OnInit {
  advice!: string;
  applicationID:number = 0;
  applicationToShow!: IApplication;

  constructor(private DB:ApplicationService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    this.applicationID = this.route.snapshot.params['id'];
    this.DB.getApplicationById(this.applicationID).subscribe(app => {
      this.applicationToShow = app;
    });
    }

  postAdvice(advice:string) {
    alert("Advies opgeslagen!");
    this.applicationToShow.advice = advice;
    this.applicationToShow.state = ApplicationState.GOEDKEURING_2;
    this.DB.updateApplication(this.applicationID,this.applicationToShow)
      .subscribe(() => this.goBack());
  }

  goBack(){
    this.router.navigate(['overview']);
  }
}
