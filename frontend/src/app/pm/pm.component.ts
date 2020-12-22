import { Observable } from "rxjs";
import { EncuestaService } from '../services/encuesta.service';
import { Encuesta } from '../services/encuesta';
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from "../services/user.service";
import { TokenStorageService } from "../auth/token-storage.service";

@Component({
  selector: 'app-pm',
  templateUrl: './pm.component.html',
  styleUrls: ['./pm.component.css']
})
export class PmComponent implements OnInit {
  id: number;
  info: any;
  board: string;
  errorMessage: string;
  encuestas: Observable<Encuesta[]>;


  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private encuestaService: EncuestaService,
    private router: Router,
    private token: TokenStorageService
  ) { }

  ngOnInit() {

    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };

    this.userService.getPMBoard(this.encuestas).subscribe(
      data => {
        this.board = data;
        this.id = this.route.snapshot.params['id'];
        this.reloadData()
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

  reloadData() {
    this.encuestas = this.encuestaService.getEncuestaList();
  }

  deleteEncuesta(id: number) {
    this.encuestaService.deleteEncuesta(id)
      .subscribe(
        (data: any) => {
          console.log(data);
          this.reloadData();
        },
        (error: any) => console.log(error));
  }

  encuestaDetails(id: number) {
    this.router.navigate(['details', id]);
  }

  updateEncuesta(id: number) {
    this.router.navigate(['update', id]);
  }

}
