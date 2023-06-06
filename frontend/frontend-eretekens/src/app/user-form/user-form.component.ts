import { Component, OnInit } from '@angular/core';
import { User } from '../entity/user';
import { UserService } from '../service/user-service';
import { Router } from '@angular/router';
import { UserRole, getRole, getRoleName } from '../enum/user-role';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  allUsers : User[] = [];
  newUser : User = {email: "", password:"", role:null ,firstname:"",lastname:""}
  roles :string[]= [getRoleName(UserRole.DEVELOPER)!,getRoleName(UserRole.ONDERZOEKER_ABB)!,getRoleName(UserRole.ONDERZOEKER_MINISTER)!,getRoleName(UserRole.ONDERZOEKER_EERSTE_MINISTER)!,getRoleName(UserRole.ONDERZOEKER_KANSELARIJ)!,getRoleName(UserRole.ONDERZOEKER_KONING)!, getRoleName(UserRole.INITIATIEFNEMER)!, getRoleName(UserRole.ADVISEUR)!, getRoleName(UserRole.BESLUITNEMER)!, getRoleName(UserRole.OORKONDEOPMAKER)!, getRoleName(UserRole.VERTALER)!];
  showNotCompleteMessage : boolean = false;
  showErrorEmailMessage : boolean = false;
  showUserCreatedMessage: boolean = false;
  emailIsValid : boolean = false;
  emailPattern : RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  constructor(public service: UserService, private router:Router) { 
    service.GetUsersObservable().subscribe(users=> this.allUsers = users);

  }
  
  ngOnInit(): void {
  }
  OnSubmit():void{
    if(this.newUser.email =="" 
    || this.newUser.password == ""
    || this.newUser.role == null)
    this.showNotCompleteMessage = true;

    else{
      this.EmailExists();
      if(!this.showErrorEmailMessage)
          {
            this.newUser.role = getRole(this.newUser.role)!;
              this.service.CreateUser(this.newUser).subscribe(
                response => {
                  this.newUser.email="";
                  this.newUser.password="";
                  this.newUser.role = null;
                  this.showUserCreatedMessage = true;
                  alert("Gebruiker aangemaakt!");
                },
                error => {
                  console.log('Error creating user:', error);
                }
              )
          }
    }
    setTimeout(() => {
      this.showNotCompleteMessage = false;
      this.showErrorEmailMessage = false;
      this.showUserCreatedMessage = false;
    }, 5000);
  }
 

  EmailExists():void{
      this.showErrorEmailMessage = this.allUsers.some(user=> user.email === this.newUser.email)
  }

  ValidateEmail(): void{
   this.emailIsValid = this.emailPattern.test(this.newUser.email);
  }

  goBack(){
    this.router.navigate(['user-managment']);
  }

  buttonDisabled():boolean{
    if(!this.emailIsValid || !this.arePropertiesValid())
    {
    return true;      
    } 
    else {
     return false;
    }
  }

  arePropertiesValid(): boolean{
    if(this.newUser.email.trim() == "" ||
      this.newUser.email == null ||
      this.newUser.firstname.trim() == ""||
      this.newUser.firstname == null ||
      this.newUser.lastname.trim() == "" ||
      this.newUser.lastname == null || 
      this.newUser.password.trim() == "" ||
      this.newUser.password == null ||
      this.newUser.role?.trim() == "" || 
      this.newUser.role == null)
      return false;
    else 
      return true;
  }
}
