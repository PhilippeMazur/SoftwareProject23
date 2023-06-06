import { Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { IABBCareer } from '../entity/ABBCareer';
import { IPerson } from '../entity/Person';

@Injectable({
  providedIn: 'root'
})
export class ABBService {

  constructor(private http: HttpClient) { }

  getPersonByNationalNumber(nationalNumber: string): Observable<IPerson> {
    const formattedNationalNumber = this.formatNN(nationalNumber);
    const url = `http://localhost:8080/abb/citizen/${formattedNationalNumber}`;

    //const url = `http://projectvm27.p.bletchley.cloud:10079/citizen/${formattedNationalNumber}`;
    //use 75011401234 as national number input

    return this.http.get<IPerson>(url).pipe(
      map((res: IPerson) => {
        return res;
      })
    );
  }

  getCareerByNationalNumber(nationalNumber: string): Observable<IABBCareer> {
    const formattedNationalNumber = this.formatNN(nationalNumber);
    const url = `http://localhost:8080/abb/employee/${formattedNationalNumber}`;

    //const url = `http://projectvm27.p.bletchley.cloud:10079/employee/${formattedNationalNumber}`;
    //use 75011401234 as national number input

    return this.http.get<IABBCareer>(url).pipe(
      map((res: IABBCareer) => {
        return res;
      })
    );
  }

  formatNN(input: String): String
  {
      // Remove any non-numeric characters from the input string
      const nn = input.replace(/[^\d]/g, '');

      // Extract the different parts of the national number
      const part1 = nn.slice(0, 2);
      const part2 = nn.slice(2, 4);
      const part3 = nn.slice(4, 6);
      const part4 = nn.slice(6, 9);
      const part5 = nn.slice(9, 11);

      //combine
      return `${part1}.${part2}.${part3}-${part4}.${part5}`;
  }

}
