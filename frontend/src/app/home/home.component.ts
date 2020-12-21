import { EmployeeService } from '../services/employee.service';
import { Employee } from '../services/employee';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: any;
  encuesta: Employee = new Employee();
  submitted = false;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['auth/login'])
      .then(() => {
        window.location.reload();
      });
  }

  newEmployee(): void {
    this.submitted = false;
    this.encuesta = new Employee();
  }

  save() {
    this.employeeService.createEmployee(this.encuesta)
      .subscribe(data => console.log(data), error => console.log(error));
    this.encuesta = new Employee();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['home']);
  }

}
