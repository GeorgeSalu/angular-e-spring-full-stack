import { Injectable } from '@angular/core';
import { Cliente } from './clientes/clientes';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(
    private http: HttpClient
  ) { }

  getCliente(): Cliente {
    let cliente: Cliente = new Cliente();
    cliente.nome = 'fulano de tal';
    cliente.cpf = '8888888888888';
    return cliente;
  }
}
