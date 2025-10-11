import { inject } from '@angular/core';
import { CanActivateFn, Route, Router } from '@angular/router';
import { AuthService } from '../../features/auth/services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService =   inject(AuthService);
  const router = inject(Router);

  if( authService.isLoggedIn()){
    return true;
  }else{
    router.navigateByUrl('/auth/login');
    return false;
  }
};
  