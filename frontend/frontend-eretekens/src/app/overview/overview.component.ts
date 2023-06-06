import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { IApplication } from '../entity/Application';
import { ApplicationService } from '../service/application-service';
import { UserService } from '../service/user-service';
import { ApplicationState, getName, getState } from '../enum/application-state';


@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css'],
})



export class OverviewComponent implements OnInit, OnDestroy {

  userRole: String = "";
  applicationsToShow: IApplication[] = [];
  allApplications : IApplication[]=[];
  destroy$: Subject<boolean> = new Subject<boolean>;
  currentYear : number = 0;
  yearList : number[] = [];
  yearFilter : number | null;
  nameFilter : string | null;
  initianatorFilter: string | null;
  applicationIdFilter : number | null;
  stateList : string[] = [getName(ApplicationState.GOEDKEURING_1)!,getName(ApplicationState.GOEDKEURING_2)!,
  getName(ApplicationState.GOEDKEURING_3)!,getName(ApplicationState.GOEDKEURING_4)!, getName(ApplicationState.GOEDKEURING_5)!, 
  getName(ApplicationState.ADVIES)!,getName(ApplicationState.BESLUIT)!,getName(ApplicationState.BESLUIT_VERTALEN)!,getName(ApplicationState.OORKONDE_OPMAKEN)!];
  stateFilter : ApplicationState | null;
  sortList : string[]= ["oudere aanvragen","nieuwere aanvragen"];
  selectedSortOption : string = "oudere aanvragen";
  filterDiv : HTMLDivElement;
  filterOptionsButton : HTMLButtonElement;
  state = ApplicationState;

  constructor(public DB: ApplicationService, public userService: UserService) { }

  public getAllApplications() {
      
    return this.DB.getAllApplications().subscribe(data => {
      this.DB.applications = data;
      this.DB.getApplicationsByRole();
      this.applicationsToShow = this.DB.applicationsToShow;
      this.allApplications = this.applicationsToShow;
    });
  }
  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
    this.userRole = this.userService.role;
    this.getAllApplications();
    this.initializeFilterVariables();
  }

  initializeFilterVariables():void{
    this.currentYear = new Date().getFullYear();  
    for(let i = 1920; i <= this.currentYear; i++)
    this.yearList.push(i);
    this.yearList.reverse();
  }

  filterApplicationList():void{
    this.applicationsToShow = this.allApplications;
    if(this.yearFilter != null && this.yearFilter !== undefined )
     this.applicationsToShow = this.applicationsToShow.filter(app =>
      {
        var year = new Date(app.dateCreated!).getFullYear();
        return year == this.yearFilter
      })
    if(this.nameFilter != "" && this.nameFilter != undefined && this.nameFilter != null)
    this.applicationsToShow = this.applicationsToShow.filter(app=> app.firstname!.toLowerCase() + " " +app.lastname!.toLowerCase() == this.nameFilter!.toLowerCase())   
    
    if(this.initianatorFilter != "" && this.initianatorFilter != undefined && this.initianatorFilter != null)
    this.applicationsToShow = this.applicationsToShow.filter(app=> app.initiator!.toLowerCase() == this.initianatorFilter!.trim().toLowerCase())   
    
    if(this.applicationIdFilter! != null && this.applicationIdFilter !== undefined)
    this.applicationsToShow = this.applicationsToShow.filter(app=> app.id! == this.applicationIdFilter);

    if(this.stateFilter !== undefined && this.stateFilter != null)
    this.applicationsToShow = this.applicationsToShow.filter(app=> app.state! == getState(this.stateFilter!));
    }

    resetFiltering():void{
      this.getAllApplications();
      this.yearFilter = null;
      this.stateFilter = null;
      this.nameFilter = null;
      this.applicationIdFilter = null;
    }
    
    sortApplicationList(sortOption: string):void {
      this.applicationsToShow = this.applicationsToShow.sort((a, b) => {
        if (sortOption === "nieuwere aanvragen") 
              return b.id! - a.id!;            
        else if(sortOption === "oudere aanvragen")
            return a.id! - b.id!; 
        else return 0;
      });
    }

    changeVisibility() {
      this.filterDiv = document.querySelector(".filter") as HTMLDivElement;
      this.filterOptionsButton = document.querySelector("#filterOptionsButton") as HTMLButtonElement;
      if (this.filterDiv.style.visibility === "visible") {
        this.filterDiv.style.visibility = "hidden";
        this.filterDiv.style.height = "0px";
        this.filterDiv.style.width="0px";
        this.filterOptionsButton.innerHTML = "Filter opties +"

      } else {
        this.filterDiv.style.visibility = "visible";
        this.filterDiv.style.height = "100%";
        this.filterDiv.style.width="100%";
        this.filterOptionsButton.innerHTML = "Filter opties -"
      }
    } 

    stateName(app: ApplicationState){
      return getName(app);
      }
}