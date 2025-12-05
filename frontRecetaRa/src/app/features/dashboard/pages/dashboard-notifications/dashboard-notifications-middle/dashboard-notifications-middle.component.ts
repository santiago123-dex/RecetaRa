import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";


@Component({
  selector: 'dashboard-notifications-middle',
  standalone: true,
  imports: [RouterLinkActive, RouterOutlet,RouterLink],
  templateUrl: './dashboard-notifications-middle.component.html',
})

export class DashboardNotificationsMiddle{

}