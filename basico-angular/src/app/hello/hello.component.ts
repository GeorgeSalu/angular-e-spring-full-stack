import { Component } from "@angular/core";

@Component({
    selector: 'hello',
    templateUrl: './hello.component.html'
})
export class HelloComponent {

    nome: string;
    clientes: Cliente[];

    constructor() {
        this.nome = "george";

        let fulano = new Cliente('fulano', 22);
        let cicrano = new Cliente('cicrano', 29);
        let outro = new Cliente('outro', 22);

        this.clientes = [fulano, cicrano, outro];
    }

}

class Cliente {

    constructor(
        public nome: string,
        public idade: number
    ) { }
}