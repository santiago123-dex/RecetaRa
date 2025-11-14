import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ApiService } from '../../../../core/services/api.service';
import { RegisterRequest, RegisterResponse } from '../../../../core/models/auth/auth.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  loading: boolean = false;
  message: string = '';
  error: string = '';

  private fb = inject(FormBuilder);
  private api = inject(ApiService);
  private router = inject(Router);


    form = this.fb.nonNullable.group({
      nombreUsuario: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contrasena: ['', [Validators.required, Validators.minLength(6)]],
    });
  

  onsubmit() {
    console.log('Form submitted'); // Debug 1
    console.log('Form value:', this.form.value); // Debug 2
    console.log('Form valid:', this.form.valid); // Debug 3
    if (this.form.invalid) {
      console.log('Form is invalid'); // Debug 4
      console.log('Form errors:', this.form.errors); // Debug 5
      return;
    }

    this.loading = true;
    this.message = '';
    this.error = ''

    console.log('Making API call...'); // Debug 6


    this.api.register(this.form.getRawValue()).subscribe({
      next: (res: RegisterResponse) => {
        console.log('Success response:', res); // Debug 7
        this.message = String(res.message) || "Usuario registrado correctamente";
        this.loading = false; // rehabilita el formulario
        this.form.reset() // limpiar formularios
        console.log('Navigating to dashboard...'); // Debug 8
        this.router.navigate(['/dashboard']); // redirige a la pagina de dashboard
      },
      error: (err) => {
        console.error('Error response:', err); // Debug 9

        this.error = "Usuario no se ha podido registrar";
        this.loading = false;
      }
    })
  }

}
