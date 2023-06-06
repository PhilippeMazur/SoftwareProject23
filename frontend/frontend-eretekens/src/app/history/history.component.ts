import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../service/application-service';
import { ApplicationState } from '../enum/application-state';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  state = ApplicationState;

  currentPage: String = "history";

  constructor(public DB: ApplicationService) { }

  public getAllApplications() {
    return this.DB.getAllApplications();
  }

  ngOnInit() {
    this.getAllApplications().subscribe(data => {
      this.DB.applications = data;
    });
  }

}
