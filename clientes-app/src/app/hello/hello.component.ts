import { Component } from "@angular/core";

@Component({
    selector: 'hello',
    templateUrl: './hello.component.html'
})
export class HelloComponent {

    nome: string;

    constructor() {
        this.nome = "george"
    }

}