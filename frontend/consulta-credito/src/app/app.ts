import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import Swal from 'sweetalert2';

import { CreditoService, Credito } from './services/credito';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatTableModule, MatProgressSpinnerModule ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'consulta-credito';
  numeroCredito = '';
  numeroNfse = '';
  isLoading = false;
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
    this.isLoading = true;
    const temNumeroNfse = this.numeroNfse && this.numeroNfse !== '';
    const temNumeroCredito = this.numeroCredito && this.numeroCredito !== '';

    if (temNumeroNfse && !temNumeroCredito) {
      this.creditoService.listaCreditosByNumeroNfse(this.numeroNfse).subscribe({
        next: (res) => {
          this.listaCreditos = res;
          this.detalhesCredito = [];
          this.isLoading = false;
          if(this.listaCreditos.length === 0){
            Swal.fire({
              icon: 'warning',
              title: 'Nenhum resultado encontrado.',
              confirmButtonColor: '#3f51b5'
            });
            return;
          }
        },
        error: (err) => {
          console.error('Erro ao buscar créditos:', err);
          this.isLoading = false;
        }
      });
    } else if (!temNumeroNfse && temNumeroCredito) {
      this.creditoService.buscarCredito(this.numeroCredito).subscribe({
        next: (res) => {
          this.detalhesCredito = res ? [res] : [];
          this.listaCreditos = [];
          this.isLoading = false;
          if(this.detalhesCredito.length === 0){
            Swal.fire({
              icon: 'warning',
              title: 'Nenhum resultado encontrado.',
              confirmButtonColor: '#3f51b5'
            });
            return;
          }
        },
        error: (err) => {
          console.error('Erro ao buscar crédito:', err);
          this.isLoading = false;
        }
      });
    } else if (!this.numeroCredito && !this.numeroNfse) {
        Swal.fire({
          icon: 'warning',
          title: 'Campos obrigatórios',
          text: 'Informe pelo menos um dos campos para realizar a busca.',
          confirmButtonColor: '#3f51b5'
        });
        this.isLoading = false;
        return;
    } else if (this.numeroCredito != null && this.numeroNfse != null) {
        Swal.fire({
          icon: 'warning',
          title: 'Preenchimento incorreto!',
          text: 'Preencha apenas um dos campos por pesquisa.',
          confirmButtonColor: '#3f51b5'
        });
        this.isLoading = false;
        return;
    }
    else {
      this.listaCreditos = [];
      this.detalhesCredito = [];
      this.isLoading = false;
    }
  }
}
