import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IApplication } from '../entity/Application';
import { ApplicationService } from '../service/application-service';
import { ApplicationState } from '../enum/application-state';

@Component({
  selector: 'app-translation',
  templateUrl: './translation.component.html',
  styleUrls: ['./translation.component.css']
})
export class TranslationComponent implements OnInit {

  decision!: string;
  translation!: string;
  applicationID:number = 0;
  applicationToShow?: IApplication;

  constructor(private DB:ApplicationService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    this.applicationID = this.route.snapshot.params['id'];
    this.DB.getApplicationById(this.applicationID).subscribe(app => {
      this.applicationToShow = app;
    });
    }

  postTranslatedDecision(decision_translated:string) {
    alert("Vertaling opgelsagen!");

    this.applicationToShow!.decisionTranslated = decision_translated;
    this.applicationToShow!.state = ApplicationState.OORKONDE_OPMAKEN;
    this.DB.updateApplication(this.applicationID,this.applicationToShow!)

      .subscribe(() => this.goBack());
  }

  goBack(){
    this.router.navigate(['overview']);
  }

}

