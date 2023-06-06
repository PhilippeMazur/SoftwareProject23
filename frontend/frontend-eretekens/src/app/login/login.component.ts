import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(public service: UserService) {

    service.GetAll();
  }

  ngOnInit(): void {
    if(localStorage.getItem("loggedIn", ))
    this.service.email = "";
    this.service.password = "";
  }

  Submit() {
    this.service.CheckLogin()

  }

}
