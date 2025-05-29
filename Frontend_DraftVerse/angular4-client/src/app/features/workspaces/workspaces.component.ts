import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateWorkspaceComponent } from '../create-workspace/create-workspace.component';


@Component({
  selector: 'app-workspaces',
  standalone:false,
  templateUrl: './workspaces.component.html',
  styleUrls: ['./workspaces.component.css']
})
export class WorkspacesComponent {
  workspaces = [
    { name: 'Workspace 1' },
    { name: 'Workspace 2' },
    { name: 'Workspace 3' },
    { name: 'Workspace 4' },
    { name: 'Workspace 5' },
    { name: 'Workspace 6' },
    { name: 'Workspace 7' },
    { name: 'Workspace 8' },
    { name: 'Workspace 9' },

  ];
  constructor(private dialog: MatDialog) {}

  viewWorkspaceDetails(workspaceName: string) {
    console.log('Viewing details for', workspaceName);
  }

  DeleteWorkspace(workspaceName: string) {
    const confirmed = window.confirm(`Are you sure you want to delete "${workspaceName}"? \n This action cannot be undone.`);
    if (confirmed) {
      this.workspaces = this.workspaces.filter(workspace => workspace.name !== workspaceName);
    }
  }

  createNewWorkspace() {
    const dialogRef = this.dialog.open(CreateWorkspaceComponent, {
      width: '400px',
      data: { workspaces: this.workspaces },
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Workspace created:', result);
        this.workspaces.push({ name: result });
      }
    });
  
}
}
