import { Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ICertificate } from '../entity/Certificate';
import { Observable, catchError, tap, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CertificateService {
private apiUrl = 'http://localhost:8080/certificate';

constructor(private http: HttpClient) { }

    postCertificate(newCertificate: ICertificate): Observable<ICertificate> {
        return this.http.post<ICertificate>(this.apiUrl, newCertificate)
        .pipe(
            catchError((err) => {
            return throwError(() => err);
            })          
        )}

      getCertficateByApplicationId(id : number){
          return this.http.get<ICertificate>(this.apiUrl + "/applicationId/" + id)
          .pipe(
              catchError((err) => {
              return throwError(() => err);
              })   
                     
          )}
      }

