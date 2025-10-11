//CONEXION GENERAL PARA LA PETICIONES HTTP
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../core.config';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class ApiService {

  private baseUrl = enviroment.apiUrl;

  constructor(private http: HttpClient) { }

  get<T>(endpoint:string): Observable<T>{
    return this.http.get<T>(`${this.baseUrl}/${endpoint}`);
  }

  post<T>(endpoint:string, data: any): Observable<T>{
    return this.http.post<T>(`${this.baseUrl}/${endpoint}`, data);
  }
  
}
