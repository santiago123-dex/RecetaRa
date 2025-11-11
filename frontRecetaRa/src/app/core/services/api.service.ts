import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../core.config";
import { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse } from "../models/auth/auth.model";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root"
})

export class ApiService{
    private http = inject(HttpClient); // Da acceso completo a peticiones http (get, post, etc..)
    private apiUrl = environment.apiUrl; //Guarda url base del back, la que esta en el config

    //Post
    register(data:RegisterRequest): Observable<RegisterResponse>{
        return this.http.post<RegisterResponse>(`${this.apiUrl}/auth/register`, data);
    }

    login(data:LoginRequest) : Observable<LoginResponse>{
        return this.http.post<LoginResponse>(`${this.apiUrl}/auth/login`, data); 
    }

}