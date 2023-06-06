import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user-service';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private service: UserService) { }

  ngOnInit(): void {

  }

  refreshSession() {
    this.service.logedIn = false
    localStorage.setItem('loggedIn', JSON.stringify(false));
    localStorage.removeItem('token')

  }
  getUserRole():string{
    return this.service.GetRoleOfUser();
  }
}
