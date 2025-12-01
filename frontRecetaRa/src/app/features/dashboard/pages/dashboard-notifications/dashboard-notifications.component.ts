import { Component } from "@angular/core";
import { CardLeft } from "../../components/sidebar-left/sidebar-left.component";
import { DashboardNotificationsMiddle } from "./dashboard-notifications-middle/dashboard-notifications-middle.component";
import { RouterOutlet } from "@angular/router";


@Component({
    selector: 'dashboard-notifications',
    standalone: true,
    imports: [CardLeft, DashboardNotificationsMiddle, RouterOutlet],
    templateUrl: './dashboard-notifications.component.html'
})

export class DashboardNotifications{

}