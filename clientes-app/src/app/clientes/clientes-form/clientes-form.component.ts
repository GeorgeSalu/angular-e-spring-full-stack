import { Component, OnInit } from '@angular/core';
import { Cliente } from '../clientes';
import { ClientesService } from 'src/app/clientes.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRouter: ActivatedRoute
  ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params = this.activatedRouter.params;

    if (params) {
      this.id = params.value.id;
      this.service
        .getClienteById(this.id)
        .subscribe(
          response => this.cliente = response,
          errorResponse => this.cliente = new Cliente()
        )
    }
  }

  onSubmit() {
    this.service
      .salvar(this.cliente)
      .subscribe(response => {
        this.success = true;
        this.errors = null;
        this.cliente = response;
      }, errorResponse => {
        this.success = false;
        this.errors = errorResponse.error.errors;
      })
  }

  voltarParaListagem() {
    this.router.navigate(['/clientes-lista'])
  }

}
