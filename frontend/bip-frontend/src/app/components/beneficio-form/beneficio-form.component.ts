import { Component, Input, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { BeneficioService } from '../../services/beneficio.service';
import { BeneficioCadastroDTO } from '../../models/beneficio';
import { OperationMode } from '../../enums/OperationMode';

@Component({
  selector: 'app-beneficio-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './beneficio-form.component.html',
  styleUrls: ['./beneficio-form.component.css']
})
export class BeneficioFormComponent implements OnInit {
  @Input() modo: OperationMode = OperationMode.NEW;
  isEdicao: boolean = false;
  isDetalhar: boolean = false;
  isCriacao: boolean = false;
  beneficioForm: FormGroup;
  beneficioId?: number;
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private beneficioService: BeneficioService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) {
    this.beneficioForm = this.criarForm();
  }

  ngOnInit(): void {
    this.getNavigationData();
    this.configurarModo();
    this.carregarBeneficio(this.beneficioId ? this.beneficioId : 0);

  }

  private getNavigationData() {
  this.route.params.subscribe(params => {
    const id = params['id'];
    
    if (id) {
      this.beneficioId = +id;
      const currentUrl = this.router.url;
      if (currentUrl.includes('/editar/')) {
        this.modo = OperationMode.EDIT;
      } else if (currentUrl.includes('/detalhar/')) {
        this.modo = OperationMode.VIEW;
      } else {
        this.modo = OperationMode.VIEW;
      }
    } else {
      this.modo = OperationMode.NEW;
    }

    console.log('Dados da navegação:', {
      beneficioId: this.beneficioId,
      modo: this.modo,
      url: this.router.url
    });

  });
}

  private configurarModo(): void {
    this.isEdicao = this.modo === OperationMode.EDIT;
    this.isDetalhar = this.modo === OperationMode.VIEW;
    this.isCriacao = this.modo === OperationMode.NEW;
  }

  

  private criarForm(): FormGroup {
    return this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      descricao: [''],
      valor: [0, [Validators.min(0)]]
    });
  }

 carregarBeneficio(id: number): void {
   if (id > 0) {
    console.log('Carregando benefício ID:', id);
      this.carregando = true;
      this.beneficioService.buscarPorId(id).subscribe({
        next: (data) => {
          console.log('Dados do benefício carregados:', data);
          this.beneficioForm.patchValue(data);
          this.carregando = false;
        },
        error: (error) => {
          console.error('Erro ao carregar benefício:', error);
          this.carregando = false;
          alert('Erro ao carregar benefício.');
        }
      });
    }
  }

  onSubmit(): void {
    if (this.beneficioForm.valid) {
      this.carregando = true;
      const dto: BeneficioCadastroDTO = this.beneficioForm.value;

      if (this.isEdicao && this.beneficioId) {
        this.beneficioService.atualizarBeneficio(this.beneficioId, dto).subscribe({
          next: (response) => {
            console.log('Benefício atualizado:', response);
            this.router.navigate(['/beneficios']);
          },
          error: (error) => {
            console.error('Erro ao atualizar:', error);
            alert('Erro ao atualizar benefício. Tente novamente.');
            this.carregando = false;
          }
        });
      } else if (this.isCriacao){
        this.beneficioService.inserirBeneficio(dto).subscribe({
          next: (response) => {
            console.log('Benefício criado:', response);
            this.router.navigate(['/beneficios']);
          },
          error: (error) => {
            console.error('Erro ao inserir:', error);
            alert('Erro ao criar benefício. Tente novamente.');
            this.carregando = false;
          }
        });
      }
    } else {
      Object.keys(this.beneficioForm.controls).forEach(key => {
        this.beneficioForm.get(key)?.markAsTouched();
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/beneficios']);
  }

  get nome() {
    return this.beneficioForm.get('nome'); 
  }

  get descricao() {
    return this.beneficioForm.get('descricao'); 
  }
  get valor() {
    return this.beneficioForm.get('valor'); 
  }
}