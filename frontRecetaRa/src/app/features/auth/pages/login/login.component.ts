import { Component, inject, signal, Signal } from '@angular/core';
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
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loading = signal(false);
  message = signal('');
  error = signal('');

  private fb = inject(FormBuilder);
  private api = inject(ApiService);
  private router = inject(Router)


  form = this.fb.nonNullable.group({
    email: ['', [Validators.required, Validators.email]],
    contrasena: ['', [Validators.required]]
  });

  onLogin() {
    if (this.form.invalid) {
      return;
    }

    this.loading.update(() => true);
    this.message.update(() => '');
    this.error.update(() => '');


    this.api.login(this.form.getRawValue()).subscribe({
      next: (res: LoginResponse) => {
        localStorage.setItem("token", String(res.token) || '');
        this.message.update(() => String(res.message) || "Login registrado correctamente");;
        this.loading.update(() => false); // reactiva el formulario
        this.form.reset() //Limpia los formularios
        this.router.navigate(['/dashboard']);

      },
      error: (err) => {
        this.error = err.error?.mensaje || "Usuario no se ha podido Loign"
        this.loading.update(() => false);
      }
    })
  }

  
}

