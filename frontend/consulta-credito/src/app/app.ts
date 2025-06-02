import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';

import Swal from 'sweetalert2';

import { CreditoService, Credito } from './services/credito';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatTableModule ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'consulta-credito';
  numeroCredito = '';
  numeroNfse = '';
  listaCreditos: Credito[] = [];
  detalhesCredito: Credito[] = [];
  displayedColumns: string[] = [
    'aliquota',
    'baseCalculo',
    'dataConstituicao',
    'numeroCredito',
    'numeroNfse',
    'simplesNacional',
    'tipoCredito',
    'valorDeducao',
    'valorFaturado',
    'valorIssqn'
  ];

  constructor(private creditoService: CreditoService) {}

  buscarCredito() {
    const temNumeroNfse = this.numeroNfse && this.numeroNfse.trim() !== '';
    const temNumeroCredito = this.numeroCredito && this.numeroCredito.trim() !== '';

    if (temNumeroNfse && !temNumeroCredito) {
      this.creditoService.listaCreditosByNumeroNfse(this.numeroNfse).subscribe({
        next: (res) => {
          console.log("res:? ", res);
          this.listaCreditos = res;
          this.detalhesCredito = [];
        },
        error: (err) => console.error('Erro ao buscar créditos:', err)
      });
    } else if (!temNumeroNfse && temNumeroCredito) {
      this.creditoService.buscarCredito(this.numeroCredito).subscribe({
        next: (res) => {
          console.log("res:? ", res);
          this.detalhesCredito = res ? [res] : [];
          this.listaCreditos = [];
        },
        error: (err) => console.error('Erro ao buscar crédito:', err)
      });
    } else if (!this.numeroCredito && !this.numeroNfse) {
        Swal.fire({
          icon: 'warning',
          title: 'Campos obrigatórios',
          text: 'Informe pelo menos um dos campos para realizar a busca.',
          confirmButtonColor: '#3f51b5'
        });
        return;
    } else {
      this.listaCreditos = [];
      this.detalhesCredito = [];
    }
  }
}
