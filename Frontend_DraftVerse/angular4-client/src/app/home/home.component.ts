import { Component } from '@angular/core';
import {CommonModule, NgOptimizedImage, ViewportScroller} from '@angular/common';
import { RouterModule } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, NgOptimizedImage],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor (
    private viewportScroller: ViewportScroller,
    private location: Location
  ) { }

  scrollToSection(sectionId: string): void {
    this.viewportScroller.scrollToAnchor(sectionId);
    const currentPath = this.location.path();
    const updatedPath = `${currentPath.split('#')[0]}#${sectionId}`;
    this.location.replaceState(updatedPath);
  }

}
