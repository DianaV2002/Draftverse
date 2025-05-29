import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation-menu',
  templateUrl: './navigation-menu.component.html',
  styleUrl: './navigation-menu.component.css'
})
export class NavigationMenuComponent {

  @Output() drawerToggle: EventEmitter<unknown> = new EventEmitter();

  constructor(private readonly router: Router){}

  goToLogin(): void {
    this.router.navigateByUrl('Login');
    this.drawerToggle.emit();
  }
  goToRegister(): void {
    this.router.navigateByUrl('Register');
    this.drawerToggle.emit();
  }

  goToHome(): void {
    this.router.navigateByUrl('Home');
    this.drawerToggle.emit();
  }

  goToAbout(): void {
    this.router.navigateByUrl('About');
    this.drawerToggle.emit();
  }

  goToContact(): void {
    this.router.navigateByUrl('Contact');
    this.drawerToggle.emit();
  }

  goToAdmin(): void {
    this.router.navigateByUrl('Admin');
    this.drawerToggle.emit();
  }

  goToProfile(): void {
    this.router.navigateByUrl('Profile');
    this.drawerToggle.emit();
  }

  goToWorkspaces(): void {
    this.router.navigateByUrl('Workspaces');
    this.drawerToggle.emit();
  }
}
