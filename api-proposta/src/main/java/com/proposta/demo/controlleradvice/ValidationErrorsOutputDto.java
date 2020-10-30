package com.proposta.demo.controlleradvice;

import java.util.ArrayList;
import java.util.List;

/*DTO para exibir os erros de uma requisição.
* É composta por duas listas: a lista dos
* erros ocorridos e uma lista de campos
* onde o erro ocorreu*/
public class ValidationErrorsOutputDto {

    /*lista de mensagens de erros*/
    private List<String> globalErrorMessages = new ArrayList<>();

    /*lista de campos de erros com exibição personalizada*/
    private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();

    public void addError(String message){
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutputDto> getFieldErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErrors.size();
    }
}
