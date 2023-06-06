import { Component } from '@angular/core';
import { UserService } from './service/user-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend-eretekens';

  constructor(public service: UserService) {
  }
}
