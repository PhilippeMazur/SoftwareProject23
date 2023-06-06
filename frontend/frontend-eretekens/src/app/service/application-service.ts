import { Injectable} from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { IApplication } from '../entity/Application';
import { ICareer } from '../entity/Career';
import { HttpClient } from '@angular/common/http';
import { UserService } from './user-service';
import { ApplicationState } from '../enum/application-state';


@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

public applicationsToShow: IApplication[] = [];

private apiUrl = 'http://localhost:8080/application';
public applications: IApplication[] = [];

constructor(private http: HttpClient, private userService: UserService) { }

postAanvragen(newApplication: IApplication): Observable<IApplication> {
  return this.http.post<IApplication>(this.apiUrl, newApplication)
  .pipe(
    catchError((err) => {
      console.log(newApplication)
      console.log('error caught in service')
      console.error(err);
      return throwError(err);
    })

  )}
  postCareer(newCareer: ICareer): Observable<ICareer> {
    return this.http.post<ICareer>(this.apiUrl + "/career", newCareer)
    .pipe(
      catchError((err) => {
        console.log('error caught in service')
        console.error(err);
        return throwError(err);
      })

    )}

    getAllApplications(): Observable<IApplication[]> {
      return this.http.get<IApplication[]>(this.apiUrl);
    }
    getApplicationById(id:number){
      return this.http.get<IApplication>("http://localhost:8080/application/"+ id);
    }

    updateApplication(id:number, application:IApplication){
      return this.http.put<IApplication>("http://localhost:8080/application/"+id, application);
    }

    getCareerById(id:number){
      return this.http.get<ICareer>("http://localhost:8080/application/career/" + id);
    }

    getCareersByApplicationId(id:number){
      return this.http.get<ICareer[]>("http://localhost:8080/application/career/applicationId/" + id);
    }

    getApplicationsByRole() {
      switch (this.userService.role.toString()) {
        case 'INITIATIEFNEMER':
           this.applicationsToShow = this.applications;
           break;
        case 'DEVELOPER':
           this.applicationsToShow = this.applications;
           break;
        case 'ONDERZOEKER_ABB':
           this.applicationsToShow = this.applications;
           break;
        case 'ADVISEUR':
           this.applicationsToShow = this.applications.filter(a => a.state === ApplicationState.ADVIES);
           break;
        case 'ONDERZOEKER_MINISTER':
           this.applicationsToShow = this.applications.filter(a => a.state === ApplicationState.GOEDKEURING_2);
           break;
        case 'ONDERZOEKER_EERSTE_MINISTER':
           this.applicationsToShow =this.applications.filter(a => a.state === ApplicationState.GOEDKEURING_3);
           break;
        case 'ONDERZOEKER_KANSELARIJ':
           this.applicationsToShow = this.applications.filter(a => a.state === ApplicationState.GOEDKEURING_4);
           break;
        case 'ONDERZOEKER_KONING':
           this.applicationsToShow = this.applications.filter(a => a.state === ApplicationState.GOEDKEURING_5);
           break;
        case 'BESLUITNEMER':
           this.applicationsToShow = this.applications.filter(a => a.state === ApplicationState.BESLUIT);
           break;
        case 'VERTALER':
           this.applicationsToShow = this.applications.filter(a => a.state === ApplicationState.BESLUIT_VERTALEN);
           break;
        case 'OORKONDEOPMAKER':
           this.applicationsToShow = this.applications.filter(a => a.state === ApplicationState.OORKONDE_OPMAKEN);
          break;
        default:
          break;
      }
    }
    deleteApplicationById(id:number){
      return this.http.delete(this.apiUrl +"/"+id);
    }
}




