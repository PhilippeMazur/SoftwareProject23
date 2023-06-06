import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pre-application',
  templateUrl: './pre-application.component.html',
  styleUrls: ['./pre-application.component.css']
})
export class PreApplicationComponent implements OnInit {

  nationalRegisterNr: string;

  public NextStep(){
    this.router.navigate(['/application', this.nationalRegisterNr]);
    }

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

}
