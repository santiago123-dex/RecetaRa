import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ApiService } from '../../../../core/services/api.service';
import { CommonModule } from '@angular/common';
import { LoginResponse } from '../../../../core/models/auth/auth.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  form: FormGroup;
  loading: boolean = false
  message: string = '';
  error: string = '';

  constructor(private fb: FormBuilder, private api: ApiService, private router: Router){
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      contrasena: ['', [Validators.required]]
    });
  }
    onLogin(){
      if(this.form.invalid){
        return;
      }

      this.loading = true;
      this.message = ''; 
      this.error = '';


      this.api.login(this.form.value).subscribe({
        next: (res: LoginResponse) => {
          localStorage.setItem("token", String(res.token) || '');
          this.message = String(res.message) || "Login registrado correctamente";
          this.loading = false;
          this.router.navigate(['/dashboard']);

        },
        error: (err) =>{
          this.error = "Usuario no se ha podido Loign"
          this.loading = false;
        }
      })
    }
  }

