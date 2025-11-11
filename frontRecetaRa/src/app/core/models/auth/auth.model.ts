export interface RegisterRequest{
    nombreUsuario: String
    email: String
    contrasena: String
}

export interface RegisterResponse{
    id?:Number
    nombreUsuario: String
    email:String
    message:String
}

export interface LoginRequest{
    email: String
    contrasena: String
}

export interface LoginResponse{
    token?:String
    message:String
}