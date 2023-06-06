import { Component, OnInit } from '@angular/core';
import { User } from '../entity/user';
import { UserService } from '../service/user-service';
import { UserRole, getRoleName } from '../enum/user-role';

@Component({
  selector: 'app-user-managment',
  templateUrl: './user-managment.component.html',
  styleUrls: ['./user-managment.component.css']
})
export class UserManagmentComponent implements OnInit {

  allUsers : User[] = [];
  showDeleteMessage : boolean = false;
  constructor(private service: UserService) { 
   service.GetUsersObservable().subscribe(users=> this.allUsers = users);
  }

  ngOnInit(): void {
  }
  RemoveUserById(deleteUser:User, index: number):void{
    if (this.allUsers.length >1) {
      if(confirm(`Weet je zeker dat je "${deleteUser.email}" wilt verwijderen?`)){
    this.service.DeleteUserById(deleteUser.id!).subscribe(
      response => {
        this.allUsers.splice(index, 1);

      },
      error => {
        console.log('Error deleting user:', error);
      }
    )};
  }
  else 
    {this.showDeleteMessage = true;
      setTimeout(() => {
        this.showDeleteMessage = false;
      }, 5000);
    }
}
  
  roleName(role: UserRole){
      return getRoleName(role);
  }
}
