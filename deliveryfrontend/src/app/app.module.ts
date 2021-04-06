import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CalendarModule } from 'primeng/calendar';
import { CheckboxModule } from 'primeng/checkbox';
import { DropdownModule } from 'primeng/dropdown';
import { CurrencyMaskModule } from 'ng2-currency-mask';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RestauranteFormComponent } from './restaurante-form/restaurante-form.component';
import { MensagemValidacaoComponent } from './mensagem-validacao/mensagem-validacao.component';
import { RestauranteFormService } from './restaurante-form/restaurante-form.service';

@NgModule({
  declarations: [AppComponent, RestauranteFormComponent, MensagemValidacaoComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    InputTextModule,
    ButtonModule,
    TableModule,
    FontAwesomeModule,
    CalendarModule,
    BrowserAnimationsModule,
    CheckboxModule,
    DropdownModule,
    FormsModule,
    CurrencyMaskModule,    
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [RestauranteFormComponent, RestauranteFormService],
  bootstrap: [AppComponent],
})
export class AppModule {}
