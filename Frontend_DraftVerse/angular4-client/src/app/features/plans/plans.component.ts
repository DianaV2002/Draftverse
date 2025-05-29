import { Component } from '@angular/core';
import{Router} from '@angular/router'
import { environment } from '../../../environments/environment';
import { plansConfig } from './plans.config'; 

@Component({
  selector: 'app-plans',
  standalone: false,
  templateUrl: './plans.component.html',
  styleUrl: './plans.component.css'
})
export class PlansComponent {
  constructor(private router: Router) {}

  plans = plansConfig;
  choosePlan(plan: string) {

    environment.plan = plan;

 
    this.router.navigate(['/auth/sign-up']);
  }
}
