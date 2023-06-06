import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user-service';
import { User } from '../entity/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  password: string = "";
  newPassword: string = "";
  newPasswordRepeat: string = "";
  validNewPassword: boolean = true;
  validNewPasswordRepeat: boolean = true;

  userToUpdate: User | null = null;

  isFoldedOut = false;

  constructor(public DB: UserService, public router:Router) { }

  ngOnInit(): void {
    
  }

  toggleFold() {
    this.isFoldedOut = !this.isFoldedOut;
  }

  checkNewPassword() : void{
    if(this.newPassword != this.password){
      this.validNewPassword = true;
    }
    else{
      this.validNewPassword = false;
    }
  }

  checkRepeat() : void{
    if(this.newPasswordRepeat === this.newPassword){
      this.validNewPasswordRepeat = true;
    }
    else{
      this.validNewPasswordRepeat = false;
    }
  }

  submitForm() {
    if(this.validNewPassword && this.validNewPasswordRepeat){
      if(this.password === this.DB.password)
      {
        this.userToUpdate = this.DB.users.find(u => u.email === this.DB.email)!;
        this.DB.UpdatePassword(this.userToUpdate, this.newPassword).subscribe();
        alert("Password changed!");
        this.router.navigate(['overview']);
      }
      else{
        alert("Incorrect password!");
      }
    }
    else{
      alert("Invalid password!");
    }
  }

  

}
