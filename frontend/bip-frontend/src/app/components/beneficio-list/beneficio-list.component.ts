import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { BeneficioService } from '../../services/beneficio.service';
import { Beneficio } from '../../models/beneficio';

@Component({
  selector: 'app-beneficio-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './beneficio-list.component.html',
  styleUrls: ['./beneficio-list.component.css']
})
export class BeneficioListComponent implements OnInit {
  beneficios: Beneficio[] = [];
  carregando = false;
  erro = '';

  constructor(
    private beneficioService: BeneficioService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.carregarBeneficios();
  }

  carregarBeneficios(): void {
    this.carregando = true;
    this.erro = '';

    this.beneficioService.listarBeneficios().subscribe({
      next: (data) => {
        this.beneficios = data;
        this.carregando = false;
      },
      error: (error) => {
        console.error('Erro ao carregar benefícios:', error);
        this.erro = 'Erro ao carregar lista de benefícios. Tente novamente.';
        this.carregando = false;
      }
    });
  }

  editarBeneficio(indiceLista: number): void {
    this.router.navigate(['/editar',  this.beneficios[indiceLista].id]);

  }

  visualizarBeneficio(indiceLista: number): void {
    this.router.navigate(['/detalhar', this.beneficios[indiceLista].id])
  }

  novoBeneficio(): void {
    this.router.navigate(['/novo']);
  }

  excluirBeneficio(indiceLista: number): void {
    if (confirm('Tem certeza que deseja excluir este benefício?') && this.beneficios[indiceLista].id) {
      this.beneficioService.excluirBeneficio(this.beneficios[indiceLista].id ).subscribe({
        next: () => {
          this.carregarBeneficios();
        },
        error: (error) => {
          console.error('Erro ao excluir benefício:', error);
          alert('Erro ao excluir benefício. Tente novamente.');
        }
      });
    }
  }
}