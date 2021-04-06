import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { RestauranteFormService } from './restaurante-form.service';


interface City {
  name: string,
  code: string
}

@Component({
  selector: 'app-restaurante-form',
  templateUrl: './restaurante-form.component.html',
  styleUrls: ['./restaurante-form.component.css']
})

export class RestauranteFormComponent implements OnInit {
  
  constructor(private formBuilder: FormBuilder, private restauranteService: RestauranteFormService) {      
  }
  
  cities!: City[];
  
  formulario!: FormGroup;
  valueAberto: boolean = false;
  valueAtivo: boolean = false; 
  
  
  clicou(){
  }
  salvar(){ 
    this.forceValidateAllFormFields(this.formulario);
    this.formulario.updateValueAndValidity();
    if (this.formulario.valid) {
      console.log(this.formulario.value)
      console.log(this.formulario.getRawValue())
    } else {
      console.log("ENTROU NO ELSE")
    }
    
  }
  
  protected forceValidateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl && control.enabled) {
        control.markAsTouched({ onlySelf: true });
        control.markAsDirty({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.forceValidateAllFormFields(control);
      }
    });
  }
  
  updateAtivo(){
    if(!this.valorAtivo.value){
      this.valorAberto.disable();
      this.valorAberto.reset();
      this.valorAberto.setValue(false);
    }else this.valorAberto.enable();
  }  
  
  get nomeRestaurante(): FormControl { return this.formulario.get('nomeRestaurante') as FormControl; }
  get taxaFrete(): FormControl { return this.formulario.get('taxaFrete') as FormControl; }
  get dataCadastro(): FormControl { return this.formulario.get('dataCadastro') as FormControl; }
  get dataAtualizacao(): FormControl { return this.formulario.get('dataAtualização') as FormControl; }
  get valorAtivo(): FormControl { return this.formulario.get('valorAtivo') as FormControl; }
  get valorAberto(): FormControl { return this.formulario.get('valorAberto') as FormControl; }
  get tipoCozinha(): FormControl { return this.formulario.get('tipoCozinha') as FormControl; } 
  
  
  cozinhas: any
  
  iniciar(){    
    /*this.valorAtivo.valueChanges.subscribe(() => this.updateAtivo());
    this.nomeRestaurante.valueChanges.subscribe(valorAtual => console.log(valorAtual));
    this.nomeRestaurante.disable();
    this.nomeRestaurante.setValue('Testando');
    this.nomeRestaurante.reset();*/
    this.restauranteService.getCozinhas().subscribe((res: any, ) => {

      this.cozinhas = res.map((value: any) =>({
        name: value.nome,
        code: value.id
      }));

      console.log(this.cozinhas);    

    }, (error: any) =>{
      console.log(error);
    })
    
  }
  
  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      nomeRestaurante: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(10)]],
      taxaFrete: [null, Validators.required],
      dataCadastro: [null, Validators.required],
      dataAtualização: [null],
      valorAtivo: [null],
      valorAberto: [null],
      tipoCozinha: [null, Validators.required]
    });
    this.iniciar()
    this.updateAtivo()
  }
  
  
  
}
