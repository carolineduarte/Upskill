/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upskill.porto.model;
import upskill.porto.exception.ElementoNaoExistenteException;
import upskill.porto.exception.NifDuplicadoException;
import upskill.porto.exception.NumeroFuncionarioDuplicadoException;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author CAD
 */
public class Autarquia implements Serializable{
    private String nome;
    private ArrayList<Pessoa> pessoas;
    
    public Autarquia(String nome) {
        this.nome = nome;
        this.pessoas = new ArrayList<Pessoa>();
    }
    public ArrayList<Pessoa> getAllPessoas() {
        Pessoa pessoa;
        ArrayList<Pessoa> lista = new ArrayList<>();
        for (int i = 0; i < this.pessoas.size(); i++) {
            pessoa = this.pessoas.get(i);
            if (!(pessoa instanceof Funcionario)) {
                Pessoa copiaPessoa = new Pessoa(pessoa);
                lista.add(copiaPessoa);
            }else {
                Funcionario funcionarioFuncionario = new Funcionario((Funcionario) pessoa);
                lista.add(funcionarioFuncionario);
            }
        }
        return lista;
    }

//////////////////////////////////////////////////
    
    public ArrayList<Pessoa> getPessoas() {
        Pessoa pessoa;
        ArrayList<Pessoa> lista = new ArrayList<>();
        for (int i = 0; i < this.pessoas.size(); i++) {
            pessoa = this.pessoas.get(i);
            if (!(pessoa instanceof Funcionario)) {
                Pessoa copia = new Pessoa(pessoa);
                lista.add(copia);
            }
        }
        return lista;
    }
    
    public void addPessoa(Pessoa pessoa) throws NifDuplicadoException {
        Pessoa p = getPessoaByNif(pessoa.getNif());
        if (p == null) {this.pessoas.add(pessoa);
        }else {
            throw new NifDuplicadoException(p.getNif() + ": NIF j ́a existe");
        }
    }
    
    public Pessoa getPessoa(long nif) {
        Pessoa pessoa = getPessoaByNif(nif);
        if(pessoa != null){
            return new Pessoa(pessoa);
        }
        return null;
    }
    
    public void removePessoa(long nif) throws ElementoNaoExistenteException {
        Pessoa pessoa = null;
        for (int i = 0; i < this.pessoas.size(); i++) {
            pessoa = this.pessoas.get(i);
            if (pessoa.getNif() == nif) {
                if (!(pessoa instanceof Funcionario)) {
                    this.pessoas.remove(i);
                    return;
                }else {throw new ElementoNaoExistenteException(nif + ": N~ao  ́euma pessoa,  ́eum funcion ́ario");
                }
            }
        }
        throw new ElementoNaoExistenteException(nif + ": N~ao existe essa pessoa");
    }
    
    public void updatePessoa(long nif, Pessoa p) throws ElementoNaoExistenteException {
        Pessoa pessoa = null;
        boolean updated = false;
        for (int i = 0; i < this.pessoas.size() && !updated; i++) {
            pessoa = this.pessoas.get(i);
            if (pessoa.getNif() == nif) {
                pessoa = p;
                updated = true;
            }
        }
        if(updated == false){
            throw new ElementoNaoExistenteException(nif + ": N~ao existe essa pessoa");
        }
    }
    
    private Pessoa getPessoaByNif(long nif) {
        Pessoa pessoa = null;
        for (int i = 0; i < this.pessoas.size(); i++) {
            pessoa = this.pessoas.get(i);
            if (pessoa.getNif() == nif) {
                return pessoa;
            }
        }
        return null;
    }
///////////////////////////////////////////////// Funcionários
    
    public ArrayList<Funcionario> getFuncionarios() {
        Pessoa pessoa;
        ArrayList<Funcionario> lista = new ArrayList<>();
        for (int i = 0; i < this.pessoas.size(); i++) {
            pessoa = this.pessoas.get(i);
            if (pessoa instanceof Funcionario) {
                Funcionario copia = new Funcionario((Funcionario) pessoa);
                lista.add(copia);
            }
        }
        return lista;
    }
    
    public void addFuncionario(Funcionario funcionario) throws NumeroFuncionarioDuplicadoException,NifDuplicadoException{
        Pessoa p = getPessoaByNif(funcionario.getNif());
        if (p == null) {
            Funcionario f = getFuncionarioByNr(funcionario.getNumeroFuncionario());
            if (f == null) {
                addPessoa(funcionario);
            }else {
                throw new NumeroFuncionarioDuplicadoException(f.getNumeroFuncionario() + ": N ́umero de Funcion ́ario j ́aexiste");
            }
        }else {
            throw new NifDuplicadoException(p.getNif() + ": NIF j ́a existe");
        }
    }
    
    public Funcionario getFuncionario(int nr) {
        Funcionario funcionario = getFuncionarioByNr(nr);
        if (funcionario != null) {
            Funcionario copia = new Funcionario(funcionario);
            return copia;
        }
        return null;
    }
    
    public void removeFuncionario(int nr) throws ElementoNaoExistenteException {
        Pessoa pessoa = null;
        Funcionario funcionario = null;
        for (int i = 0; i < this.pessoas.size(); i++) {
            pessoa = this.pessoas.get(i);
            if (pessoa instanceof Funcionario) {
                if (pessoa instanceof Funcionario) {
                    funcionario = (Funcionario) pessoa;
                }
                if (funcionario.getNumeroFuncionario() == nr) {
                    this.pessoas.remove(i);
                    return;
                }
            }
        }throw new ElementoNaoExistenteException(nr + ": Não existe esse funcionário");
    }
    
    public void updateFuncionario(int nr, Funcionario f) throws ElementoNaoExistenteException {
        boolean updated = false;
        Pessoa pessoa = null;
        Funcionario funcionario = null;
        for (int i = 0; i < this.pessoas.size() && !updated; i++) {
            pessoa = this.pessoas.get(i);
            if (pessoa instanceof Funcionario) {
                funcionario = (Funcionario) pessoa;
                if (funcionario.getNumeroFuncionario() == nr) {
                    funcionario = f;
                    updated = true;
                }
            }
        }
        if(updated == false){
            throw new ElementoNaoExistenteException(nr + ": N~ao existe esse funcionario");
        }
    }
    
    private Funcionario getFuncionarioByNr(int nr) {
        Pessoa pessoa = null;
        Funcionario funcionario = null;
        for (int i = 0; i < this.pessoas.size(); i++) {
            pessoa = this.pessoas.get(i);
            if (pessoa instanceof Funcionario) {
                funcionario = (Funcionario) pessoa;
                if (funcionario.getNumeroFuncionario() == nr) {
                    return funcionario;
                }
            }
        }
        return null;
    }
}
