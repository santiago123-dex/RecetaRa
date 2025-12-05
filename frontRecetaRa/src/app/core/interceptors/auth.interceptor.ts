

//Esto se hace con una funcion para que sea mas ligero y moderno

import {  HttpInterceptorFn } from "@angular/common/http";

export const authInterceptor: HttpInterceptorFn = (req, next) => {





    const token = localStorage.getItem('token');


    if(token){

        //Clonamos la peticion
        const clonedRequest = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
        // se pasa el clon de la petición 
        return next(clonedRequest);
    }

    //Si no hay token se deja pasar la peticion si nada
    return next(req);
}