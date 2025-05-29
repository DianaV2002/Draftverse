import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { HomeComponent } from './home/home.component';
import { WorkspacesComponent } from './features/workspaces/workspaces.component';
import { PlansComponent } from './features/plans/plans.component';


export const routes: Routes = [
    {
    path: '',
      component: MainLayoutComponent,
      children: [
        { path: '', redirectTo: 'Home', pathMatch: 'full'},
        { path: 'Home', component: HomeComponent },
        { path: 'Workspaces', component: WorkspacesComponent },
        { path: 'Plans', component: PlansComponent },
        { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule) },
        { path: 'core', loadChildren: () => import('./core/core.module').then(m => m.CoreModule) },
        { path: '**', redirectTo: '' }
    ]
},
//{ path: '', component: MainLayoutComponent }, // Home route as the default

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    anchorScrolling: 'enabled',
    scrollPositionRestoration: 'enabled'
  }),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
