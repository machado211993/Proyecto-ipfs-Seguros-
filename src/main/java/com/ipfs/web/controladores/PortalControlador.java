
package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Usuario;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    /*
     * @Autowired
     * private ProductoServicio productoServicio;
     * 
     * @Autowired
     * private OfertaServicio ofertaServicio;
     */

    @GetMapping("/registrar") // registro cliente formulario
    public String registrar() {
        return "usuario_registrar.html";
    }

    @PostMapping("/registro") // cliente registrado
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            String password2, ModelMap modelo/* , MultipartFile archivo */) {

        try {
            usuarioServicio.registrar(/* archivo, */ nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "usuario_registrar.html";
        }

    }

    // login
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("error", "Usuario o Contraseña invalidos!");
        }

        return "login.html";
    }

    // inicio
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        /*
         * //LISTADO PARA RECORRER LAS CARTAS EN INICIO Y CAROUSEL
         * List<Producto> productos = productoServicio.listarProductos();
         * modelo.addAttribute("productos", productos);
         * List<Oferta> ofertas = ofertaServicio.listarOfertas();
         * modelo.addAttribute("ofertas", ofertas);
         */

        return "inicio.html";
    }

    // funcionalidad para modificar cliente EN SESSION
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "usuario_modificar.html";
    }

    // funcionalidad para modificar cliente por id
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("usuario", usuarioServicio.getOne(id));

        return "usuario_modificar.html";
    }

    // funcionalidad para modificar cliente por id
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar(/* @RequestParam MultipartFile archivo, */ @PathVariable String id,
            @RequestParam String nombre, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

        try {
            usuarioServicio.actualizar(/* archivo, */ id, nombre, email, password, password2);

            modelo.put("exito", "Usuario actualizado correctamente!");

            return "inicio.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "usuario_modificar.html";
        }

    }

    // funcionalidad para devolver productoServicio lista y ofertaServicio lista y
    // usuarioServicio lista EN INDEX
    /*
     * @GetMapping("/")
     * public String listar(ModelMap modelo) {
     * List<Producto> productos = productoServicio.listarProductos();
     * modelo.addAttribute("productos", productos);
     * List<Oferta> ofertas = ofertaServicio.listarOfertas();
     * modelo.addAttribute("ofertas", ofertas);
     * List<Usuario> usuarios = usuarioServicio.listarUsuarios();
     * modelo.addAttribute("usuarios", usuarios);
     * return "index";
     * 
     * }
     */

    // @GetMapping("/listarUsuarios") //lista que devuelve usuarios a la vista
    // usuario list.
    // public String listarUsuarios(ModelMap modelo, @Param("palabraClave") String
    // palabraClave) {
    // List<Usuario> usuarios = usuarioServicio.listAll(palabraClave);
    // List<Usuario> usuarios = usuarioServicio.listarUsuarios();
    // modelo.addAttribute("usuarios", usuarios);
    // modelo.addAttribute("palabraClave", palabraClave);
    // return "usuario_list";
    //
    // }
    // funcionalidad para busqueda personaliza en tabla de clientes
    @GetMapping("/listarUsuarios")
    public String listar(ModelMap modelo, @Param("palabraClave") String palabraClave) {
        List<Usuario> usuarios = usuarioServicio.listAll(palabraClave);
        modelo.addAttribute("usuarios", usuarios);
        modelo.addAttribute("palabraClave", palabraClave);
        return "usuario_list";
    }

    // funcionalidad para devolver productoServicio imagen
    // @GetMapping("/{idProducto}") //INDEX
    // public ResponseEntity<byte[]> imagenProducto(@PathVariable String idProducto)
    // {
    //
    // Producto producto = productoServicio.getOne(idProducto);
    //
    // byte[] imagen = producto.getImagen().getContenido();
    //
    // HttpHeaders headers = new HttpHeaders();
    //
    // headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de
    // tipo JPEG
    //
    // return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    // }
    //
    // //funcionalidad para devolver ofertaServicio imagen
    // @GetMapping("/{idOferta}") //INDEX
    // public ResponseEntity<byte[]> imagenOferta(@PathVariable String idOferta) {
    //
    // Oferta oferta = ofertaServicio.getOne(idOferta);
    //
    // byte[] imagen = oferta.getImagen().getContenido();
    //
    // HttpHeaders headers = new HttpHeaders();
    //
    // headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de
    // tipo JPEG
    //
    // return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    // }
    //
    // //funcionalidad para devolver productoServicio imagen
    // @GetMapping("/inicio/{idProducto}") //EN INICIO
    // public ResponseEntity<byte[]> imagenProductoo(@PathVariable String
    // idProducto) {
    //
    // Producto producto = productoServicio.getOne(idProducto);
    //
    // byte[] imagen = producto.getImagen().getContenido();
    //
    // HttpHeaders headers = new HttpHeaders();
    //
    // headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de
    // tipo JPEG
    //
    // return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    // }
    //
    // funcionalidad para devolver ofertaServicio imagen
    // @GetMapping("/inicio/{idOferta}") //EN INICIO
    // public ResponseEntity<byte[]> imagenOfertaa(@PathVariable String idOferta) {
    //
    // Oferta oferta = ofertaServicio.getOne(idOferta);
    //
    // byte[] imagen = oferta.getImagen().getContenido();
    //
    // HttpHeaders headers = new HttpHeaders();
    //
    // headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de
    // tipo JPEG
    //
    // return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    // }
    // PARA ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) { // variable de ruta

        modelo.put("usuario", usuarioServicio.getOne(id));
        return "eliminar_usuario.html";
    }

    // PARA ELIMINAR
    @PostMapping("/eliminado/{id}")
    public String eliminado(@PathVariable String id, ModelMap modelo) {

        usuarioServicio.borrarPorId(id);

        return "redirect:../listarUsuarios"; // retornar a listarUsuarios
    }

    /*
     * @GetMapping("/exportarPDF")
     * public void exportarListadoDeUsuariosEnPDF(HttpServletResponse response)
     * throws IOException {
     * response.setContentType("application/pdf");
     * DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
     * String fechaActual = dateFormatter.format(new Date());
     * String cabecera = "Content-Disposition";
     * String valor = "attachment; filename=Clientes_" + fechaActual + ".pdf";
     * response.setHeader(cabecera, valor);
     * 
     * List<Usuario> usuarios = usuarioServicio.listarUsuarios(); //cargo la lista
     * UsuarioExporterPDF exporter = new UsuarioExporterPDF(usuarios);
     * exporter.exportar(response);
     * 
     * }
     */
}
