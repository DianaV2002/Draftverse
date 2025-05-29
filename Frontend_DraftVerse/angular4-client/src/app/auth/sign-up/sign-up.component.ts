import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  signUpForm: FormGroup;
  isSubmitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {
    this.signUpForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    this.isSubmitted = true;
    if (this.signUpForm.valid) {
      this.authService.register(this.signUpForm.value).subscribe(
        response => {
          console.log('Signup successful', response);
        },
        error => {
          console.error('Signup failed', error);
        }
      );
    }
  }
}
