import { Component, Input, Optional, Self } from '@angular/core';
import { ControlValueAccessor, NgControl } from '@angular/forms';

@Component({
    selector: 'app-mensagem-validacao',
    templateUrl: './mensagem-validacao.component.html',
    styleUrls: ['./mensagem-validacao.component.scss']
})
export class MensagemValidacaoComponent implements ControlValueAccessor {

    @Input() type!: string;
    @Input() errorMessage!: string;

    control!: NgControl;

    constructor(@Optional() @Self() control: NgControl) {
        if (control) {
            control.valueAccessor = this;
            this.control = control;
        }
    }

    hasInvalidateType() {
        return (this.control.dirty || this.control.touched) && this.control.invalid && (this.control as any).errors[this.type];; // Tirar o this.control.touched para aparecer apenas depois do submit
    }


    onChange = (value: any) => { };
    onTouched = () => { };


    writeValue(obj: any): void {
    }
    registerOnChange(fn: any): void {
        this.onChange = fn;
    }
    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
    setDisabledState?(isDisabled: boolean): void {
    }

}
