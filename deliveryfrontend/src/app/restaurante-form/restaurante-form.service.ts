import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cozinha } from '../cozinha/cozinha.module';


@Injectable({
  providedIn: 'root'
})
export class RestauranteFormService {

  cozinhasUrl = 'http://localhost:8080/cozinhas'

  constructor(private http: HttpClient) { }  


  getCozinhas(): Observable<Cozinha[]> {
    return this.http.get<Cozinha[]>(`${this.cozinhasUrl}/`);
  }
}
