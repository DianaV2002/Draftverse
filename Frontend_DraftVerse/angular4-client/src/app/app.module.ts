import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LayoutModule } from './layout/layout.module';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms'; 
import {NavigationMenuComponent} from './layout/header/navigation-menu/navigation-menu.component'
import { WorkspacesComponent } from './features/workspaces/workspaces.component';
import { CreateWorkspaceComponent } from './features/create-workspace/create-workspace.component';
import { PlansComponent } from './features/plans/plans.component';


@NgModule({
  declarations: [
    AppComponent, 
    WorkspacesComponent,
    CreateWorkspaceComponent,
    PlansComponent

    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    LayoutModule,
    HttpClientModule,
    RouterModule,
    HomeComponent, 
    FormsModule
    

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
