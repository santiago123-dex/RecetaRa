import { Routes } from '@angular/router';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/landing/login/login.component';
import { RegisterComponent } from './components/landing/register/register.component';

export const routes: Routes = [
    { path:'', component: LandingComponent},
    { path: 'login', component : LoginComponent},
    { path: 'register', component : RegisterComponent}
];
