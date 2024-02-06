
package com.ipfs.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
     @GetMapping("/registrar") 
    public String registrar(ModelMap modelo) {  //metodo registro formulario
        return "cliente_form";

    }
}
