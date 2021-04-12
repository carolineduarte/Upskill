/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upskill.porto.dto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
/**
 *
 * @author CAD
 */
@JacksonXmlRootElement(localName = "pessoas")
public class ListaPessoaDTO {
   @JacksonXmlElementWrapper(useWrapping = false)
   @JacksonXmlProperty(localName = "pessoa")
   
   private ArrayList<ListaPessoaDTO> pessoas;
   
   public ListaPessoaDTO() {
   }
   
   public ArrayList<ListaPessoaDTO> getPessoas() {
       return pessoas;
   }
   
   public void setPessoas(ArrayList<ListaPessoaDTO> pessoas) {
       this.pessoas = pessoas;
   }
}
