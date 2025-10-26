import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Beneficio, BeneficioCadastroDTO } from '../models/beneficio';

@Injectable({
  providedIn: 'root'
})
export class BeneficioService {
  private apiUrl = 'http://127.0.0.1:8080/api/v1/beneficios';

  constructor(private http: HttpClient) { }

  listarBeneficios(): Observable<Beneficio[]> {
    return this.http.get<Beneficio[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/${id}`);
  }

  inserirBeneficio(dto: BeneficioCadastroDTO): Observable<Beneficio> {
    return this.http.post<Beneficio>(`${this.apiUrl}/inserir`, dto);
  }

  atualizarBeneficio(id: number, dto: BeneficioCadastroDTO): Observable<Beneficio> {
    return this.http.put<Beneficio>(`${this.apiUrl}/Atualizar/${id}`, dto);
  }

  excluirBeneficio(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}