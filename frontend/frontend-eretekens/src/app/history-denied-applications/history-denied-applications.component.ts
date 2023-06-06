import { Component, OnInit } from '@angular/core';
import { ApplicationState } from '../enum/application-state';
import { ApplicationService } from '../service/application-service';
import { IApplication } from '../entity/Application';
import { Router } from '@angular/router';

@Component({
  selector: 'app-history-denied-applications',
  templateUrl: './history-denied-applications.component.html',
  styleUrls: ['./history-denied-applications.component.css']
})
export class HistoryDeniedApplicationsComponent implements OnInit {

  state = ApplicationState;
  isFoldedOut = false;
  comment: string = "";
  validComment: boolean = true;
  applicationToUpdate: IApplication | null = null;
  applicationID: number = 0;
  deniedApplications: IApplication[] = [];
  currentPage: String = "history-denied-applications";
  constructor(public DB: ApplicationService, public router: Router) { }

  public getAllApplications() {
    return this.DB.getAllApplications();
  }

  ngOnInit() {
    this.getAllApplications().subscribe(data => {
      this.deniedApplications = data.filter(application => application.state == this.state.AFGEKEURD);
    });
  }

  checkComment() : void{
    if(this.comment != "") {
      this.validComment = true;
    }
    else{
      this.validComment = false;
    }
  }

  submitForm() {
    if(this.validComment && this.applicationToUpdate != null){      
        this.applicationToUpdate.comment = this.comment;
        if(this.applicationToUpdate.id != undefined)
        {
          this.applicationToUpdate.state = ApplicationState.KLAAR;
          this.DB.updateApplication(this.applicationID, this.applicationToUpdate).subscribe();
          alert("Commentaar succesvol toegevoegd!");
          this.isFoldedOut = false;
        }
        else{
          alert("Aanvraag niet gevonden!");
        }
      }
      else{
        alert("Fout tijdens bijwerken van de applicatie!");
      }
  }

  selectApplication(application: IApplication) {
    this.applicationToUpdate = application;
    if(application.id != undefined)
    {
      this.applicationID = application.id;
    }
  }
  
  toggleFold() {
    this.isFoldedOut = !this.isFoldedOut;
  }

  deleteRejectedApplication(applicationId: number, listId:number):void{
    if(confirm(`Weet je zeker dat je de aanvraag met id "${applicationId}" wilt verwijderen?`)){
    this.DB.deleteApplicationById(applicationId).subscribe(
        response => {
          this.deniedApplications.splice(listId, 1);
          setTimeout(() => {
            alert("Aanvraag succesvol verwijderd");
            }, 100);
            
        },
        error => {
          console.log('Error deleting user:', error);
        }
    );
  }  }
}
