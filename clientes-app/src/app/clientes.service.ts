import { Injectable } from '@angular/core';
import { Cliente } from './clientes/clientes';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(
    private http: HttpClient
  ) { }

  salvar(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>('http://localhost:8080/api/clientes', cliente);
  }

  getCliente(): Observable<Cliente[]> {
    return null;
  }

  getClientes(): Cliente[] {
    let cliente = new Cliente();
    cliente.id = 1;
    cliente.nome = 'fulano';
    cliente.dataCadastro = '18/11/2020';
    cliente.cpf = '93828323232';
    return [cliente];
  }
}
