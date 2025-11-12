import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";
import { CardMiddle } from './card-middle/card-middle.component';
import { CardLeft } from './sidebar-left/sidebar-left.component';

@Component({
  selector: 'app-dashboard-home',
  standalone: true,
  imports: [RouterLink, CardMiddle, CardLeft],
  templateUrl: './dashboard-home.component.html',
  styleUrl: './dashboard-home.component.css'
})
export class DashboardHomeComponent {

}
