import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, catchError, finalize, map, tap } from 'rxjs';
import { User } from '../entity/user';
import { UserRole } from '../enum/user-role';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private client: HttpClient, private router: Router) {
    this.GetAll();
   }

  public users: User[] = [];
  public email: String = "";
  public password: String = "";
  public firstname: String = "";
  public lastname: String = "";
  public logedIn: boolean = false;
  public throwError: boolean = false;
  public role: UserRole;

  private  baseURL: string = "http://localhost:8080/users";

  GetAll() {
    return this.client.get<User[]>(this.baseURL).subscribe(res => this.users = res)
  }
  GetUsersObservable(): Observable<User[]> {
    return this.client.get<User[]>(this.baseURL).pipe(
      map(res => res)
    );
  }
  DeleteUserById(id: number): Observable<any> {
    return this.client.delete<User>(this.baseURL+"/"+id)
      .pipe(
        catchError(err => {
          console.log('Error deleting user:', err);
          throw err;
        })
      );
  }
  CreateUser(user:User):Observable<User>{
    return this.client.post<User>(this.baseURL, user)
    .pipe(
      catchError(err => {
        console.log('Error creating user:', err);
        throw err;
      })
    );
  }

  CheckLogin() {
    const user = { "email": this.email, "password": this.password };
    this.login(user.email.toString(), user.password.toString()).subscribe(
      (response: any) => {
        this.GetUserByEmail(this.email.toString()).subscribe((user:User)=>{
          
          this.role = user.role!;
          this.firstname = user.firstname;
          this.lastname = user.lastname;
          this.logedIn = true;
          localStorage.setItem('loggedIn', JSON.stringify(true));
          localStorage.setItem('token', response.accessToken);
          this.router.navigate(['/overview']);
        })
      },
      (error) => {
        this.throwError = true;
        this.logedIn = false;
        localStorage.setItem('loggedIn', JSON.stringify(false));
        setInterval(() => this.throwError = false, 3000)
      }
    );
  }
  login(email:string, password:string) {
    const loginData = {
      email: email,
      password: password
    };

    return this.client.post<string>(`${this.baseURL}/login`, loginData);
  }

    GetUserByEmail(email: string): Observable<User> {
      const url = `${this.baseURL}/email=${email}`;
      return this.client.get<User>(url);
    }

  GetRoleOfUser():string{
    return this.users.filter(user => user.email == this.email)[0].role!;
  }

  UpdatePassword(userToChange: User, newPassword: string): Observable<User> {
    const id = userToChange.id;
    const user = this.users.find(u => u.id === id);

    if (!user) {
      throw new Error(`User with id ${id} not found`);
    }
    else
    { 
      user.password = newPassword;
      return this.client.put<User>(this.baseURL+"/"+id, user)
        .pipe(
          catchError(err => {
            throw err;
          })
        );}
  }
}
