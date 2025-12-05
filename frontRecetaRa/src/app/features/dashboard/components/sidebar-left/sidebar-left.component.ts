import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive } from "@angular/router";


@Component({
    selector: 'sidebar-left',
    standalone: true,
    imports: [RouterLink, RouterLinkActive],
    templateUrl: './sidebar-left.component.html',
    styleUrl : './sidebar-left.component.css',
})

export class CardLeft{
    
}