import { Component } from "@angular/core";
import { CardInformation } from "../components/card-information/card-information-component";


@Component({
    selector: 'dashboard-notifications-all',
    standalone: true,
    imports: [CardInformation],
    templateUrl: './dashboard-notifications-all.component.html'
})


export class DashboardNotificationsAll{

}