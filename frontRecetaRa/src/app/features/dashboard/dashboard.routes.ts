import { Routes } from "@angular/router";
import { DashboardHomeComponent } from "./pages/dashboard-home/dashboard-home.component";
import { DashboardNotifications } from "./pages/dashboard-notifications/dashboard-notifications.component";
import { DashboardNotificationsAll } from "./pages/dashboard-notifications/dashboard-notifications-all/dashboard-notifications-all.component";
import { DashboardNotificationsSave } from "./pages/dashboard-notifications/dashboard-notifications-save/dashboard-notifications-save.component";
import { DashboardNotificationsMiddle } from "./pages/dashboard-notifications/dashboard-notifications-middle/dashboard-notifications-middle.component";

export const DASHBOARD_ROUTES: Routes = [
    {
        path: '',
        children: [
            {
                path: '',
                component: DashboardHomeComponent
            },

            {
                path: 'notifications',
                component : DashboardNotifications,
                children: [
                    {
                        path: '',
                        component: DashboardNotificationsMiddle,
                        children: [
                            {
                                path: '',
                                component: DashboardNotificationsAll
                            },
                            {
                                path:'nuevas',
                                component: DashboardNotificationsSave
                            }
                        ]
                    }
                ]
            }
        ]
    
    }
]

