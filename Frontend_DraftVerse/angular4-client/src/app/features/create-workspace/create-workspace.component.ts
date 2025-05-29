import { MatDialogRef } from '@angular/material/dialog';
import { Component } from '@angular/core';

@Component({
  selector: 'app-create-workspace',
  templateUrl: './create-workspace.component.html',  
  styleUrls: ['./create-workspace.component.css']  
})

export class CreateWorkspaceComponent {
  workspaceName = '';

  constructor(public dialogRef: MatDialogRef<CreateWorkspaceComponent>) {}

  submit() {
    if (this.workspaceName.trim()) {
      this.dialogRef.close(this.workspaceName); 
    } else {
      alert('Workspace name cannot be empty!');
    }
  }

  cancel() {
    this.dialogRef.close();
  }
}
