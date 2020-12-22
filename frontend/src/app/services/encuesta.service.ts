import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EncuestaService {

  private baseUrl = 'http://localhost:8084/api/v1/encuesta';

  constructor(private http: HttpClient) { }

  getEncuesta(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createEncuesta(encuesta: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, encuesta);
  }

  updateEncuesta(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteEncuesta(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getEncuestaList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
