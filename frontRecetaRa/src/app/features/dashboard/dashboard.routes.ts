import { Routes } from "@angular/router";
import { DashboardHomeComponent } from "./pages/dashboard-home/dashboard-home.component";
import { DashboardNotifications } from "./pages/dashboard-notifications/dashboard-notifications.component";
import { DashboardNotificationsAll } from "./pages/dashboard-notifications/dashboard-notifications-all/dashboard-notifications-all.component";

export const DASHBOARD_ROUTES: Routes = [
    { path: '', component:DashboardHomeComponent},
    {
        path: 'notifications',
        component: DashboardNotifications,
        children: [ 
            {
                path:'all', component: DashboardNotificationsAll
            }
        ]
       
    }
]