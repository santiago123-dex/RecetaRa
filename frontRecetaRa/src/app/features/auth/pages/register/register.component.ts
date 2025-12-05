import { Component, Inject, inject, signal } from '@angular/core';
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
  loading = signal(false);
  message = signal('');
  error = signal('');

  private fb = inject(FormBuilder);
  private api = inject(ApiService);
  private router = inject(Router);


    form = this.fb.nonNullable.group({
      nombreUsuario: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contrasena: ['', [Validators.required, Validators.minLength(6)]],
    });
  

  onsubmit() {
    if (this.form.invalid) {
      return;
    }

    this.loading.update(() => true);
    this.message.update(() => '');
    this.error.update(() => '');



    this.api.register(this.form.getRawValue()).subscribe({
      next: (res: RegisterResponse) => {
        this.message.update(() =>String(res.message) || "Usuario registrado correctamente");  
        this.loading.update(() => false); // rehabilita el formulario
        this.form.reset() // limpiar formularios
        this.router.navigate(['/dashboard']); // redirige a la pagina de dashboard
      },
      error: (err) => {
        this.error.update(()=>"Usuario no se ha podido registrar" ); 
        this.loading.update(()=> false);
      }
    })
  }

}
