import { Routes } from '@angular/router';
import { BeneficioListComponent } from './components/beneficio-list/beneficio-list.component';
import { BeneficioFormComponent } from './components/beneficio-form/beneficio-form.component';

export const routes: Routes = [
  { path: '', redirectTo: '/beneficios', pathMatch: 'full' },
  { path: 'beneficios', component: BeneficioListComponent },
  { path: 'novo', component: BeneficioFormComponent },
  { path: 'editar/:id', component: BeneficioFormComponent },
  { path: 'detalhar/:id', component: BeneficioFormComponent },
  { path: '**', redirectTo: '/beneficios' }
];