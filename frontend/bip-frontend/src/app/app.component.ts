import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterModule, RouterOutlet],
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="container">
        <a class="navbar-brand" routerLink="/beneficios">
          <i class="fas fa-hand-holding-heart me-2"></i>
          Sistema de Benefícios
        </a>
        <div class="navbar-nav">
          <a class="nav-link" routerLink="/beneficios" routerLinkActive="active">
            Benefícios
          </a>
          <a class="nav-link" routerLink="/novo" routerLinkActive="active">
            Novo Benefício
          </a>
        </div>
      </div>
    </nav>
    
    <div class="container mt-4">
      <router-outlet></router-outlet>
    </div>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Sistema de Benefícios';
}