import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { UserService } from './service/user-service';
import { AuthService } from './service/auth.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

  /*private loggedIn = false;


  constructor(private service: UserService, private router: Router) {
    const storedLoggedIn = localStorage.getItem('loggedIn');
    this.loggedIn = storedLoggedIn ? JSON.parse(storedLoggedIn) : this.service.logedIn;
  }*/
  constructor(private authService: AuthService, private router: Router) {}


  /*canActivate{
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (localStorage.getItem("loggedIn") == JSON.stringify(true)) {
      return true;
    } else {
      this.router.navigate(["/login"])
      return false
    }

  }*/
  canActivate(): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

  canLoad(route: Route, segments: UrlSegment[]): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (localStorage.getItem("loggedIn") == JSON.stringify(true)) {
      return true;
    } else {
      this.router.navigate(["/login"])
      return false
    }
  }

}
