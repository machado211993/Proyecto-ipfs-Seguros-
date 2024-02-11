
package com.ipfs.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
    @GetMapping("/")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

       /* if (error != null) {
            modelo.put("error", "Usuario o Contraseña invalidos!");
        }*/

        return "index.html";
    }
}
