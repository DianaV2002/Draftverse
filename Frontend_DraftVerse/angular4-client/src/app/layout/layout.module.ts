import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { RouterModule } from '@angular/router';
import { NavigationMenuComponent } from './header/navigation-menu/navigation-menu.component';


@NgModule({
  declarations: [
    MainLayoutComponent,
    HeaderComponent,
    FooterComponent,
    NavigationMenuComponent
  ],
    imports: [
        CommonModule,
        RouterModule,
        NgOptimizedImage,
        //  HeaderComponent,
        //FooterComponent

    ],
  exports: [
    MainLayoutComponent,
    HeaderComponent
  ]

})
export class LayoutModule { }
