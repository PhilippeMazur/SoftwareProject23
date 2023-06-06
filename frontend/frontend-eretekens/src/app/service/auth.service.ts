import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated(): boolean {
    const loggedIn = localStorage.getItem('loggedIn');
    return loggedIn ? JSON.parse(loggedIn) : false;
  }

}
