import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { ApiService } from '../../../core/services/api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private api: ApiService) {}

  //POST LOGIN
  login(credentials: {email: string; passwordHash: string}): Observable<any>{
    return this.api.post('auth/login', credentials)
  }
  register(user: { username: string; email: string; password: string }): Observable<any> {
    return this.api.post('auth/register', user);
  }
}
