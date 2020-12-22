import { EncuestaService } from '../services/encuesta.service';
import { Encuesta } from '../services/encuesta';
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
  encuesta: Encuesta = new Encuesta();
  submitted = false;

  constructor(
    private encuestaService: EncuestaService,
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

  newEncuesta(): void {
    this.submitted = false;
    this.encuesta = new Encuesta();
  }

  save() {
    this.encuestaService.createEncuesta(this.encuesta)
      .subscribe(data => console.log(data), error => console.log(error));
    this.encuesta = new Encuesta();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['home'])
      .then(() => {
        window.location.reload();
      });
  }

}
