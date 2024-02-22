
package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Usuario;
import com.ipfs.web.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    /*@Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private OfertaServicio ofertaServicio;*/

    @GetMapping("/dashboard")
    public String panelAdministrativo(ModelMap modelo) {
        /*List<Producto> productos = productoServicio.listarProductos();
        modelo.addAttribute("productos", productos);
        List<Oferta> ofertas = ofertaServicio.listarOfertas();
        modelo.addAttribute("ofertas", ofertas);*/
        return "panel.html";
    }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "usuario_lista";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) {
        usuarioServicio.cambiarRol(id);

        return "redirect:/admin/usuarios";
    }

    //funcionalidad para devolver productoServicio lista y ofertaServicio lista
   

    /*@GetMapping("/dashboard/{idProducto}")
    public ResponseEntity<byte[]> imagenProducto(@PathVariable String idProducto) {

        Producto producto = productoServicio.getOne(idProducto);

        byte[] imagen = producto.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de tipo JPEG

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

    //funcionalidad para devolver ofertaServicio imagen
    @GetMapping("/dashboard/{idOferta}")
    public ResponseEntity<byte[]> imagenOferta(@PathVariable String idOferta) {

        Oferta oferta = ofertaServicio.getOne(idOferta);

        byte[] imagen = oferta.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de tipo JPEG

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }*/
 
}
