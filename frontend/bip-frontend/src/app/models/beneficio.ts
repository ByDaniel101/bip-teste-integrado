export interface Beneficio {
  id?: number;
  nome: string;
  descricao?: string;
  valor?: number;
  ativo?: boolean;
}

export interface BeneficioCadastroDTO {
  nome: string;
  descricao?: string;
  valor?: number;
}