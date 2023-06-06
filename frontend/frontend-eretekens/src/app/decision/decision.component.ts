import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IApplication } from '../entity/Application';
import { ApplicationService } from '../service/application-service';
import { ApplicationState } from '../enum/application-state';

@Component({
  selector: 'app-decision',
  templateUrl: './decision.component.html',
  styleUrls: ['./decision.component.css']
})
export class DecisionComponent implements OnInit {

  decision: string;
  applicationID: number = 0;
  applicationToShow!: IApplication;

  constructor(private DB:ApplicationService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    this.applicationID = this.route.snapshot.params['id'];
    this.DB.getApplicationById(this.applicationID).subscribe(app => {
      this.applicationToShow = app;
    });
    }

  postDecision(decision:string) {
    this.applicationToShow.decision = decision;
    this.applicationToShow.state = ApplicationState.BESLUIT_VERTALEN
    this.DB.updateApplication(this.applicationID,this.applicationToShow)
      .subscribe(() => this.goBack());
  }

  goBack(){
    alert("belsuit opgelsagen");
    this.router.navigate(['overview']);
  }

}
