package model;

/**
 *
 * @author Bruno Lucas, Jhonatan Nascimento, Maikon Sposito
 */
public class Fornecedores extends Cliente{
    //Atributos
    private String cnpj;

    //Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    //Para chamar o objeto Fornecedores junto com seus atributos, e mostrar o nome
    @Override
    public String toString(){
        return this.getNome();     
    }
    
}
