package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Cliente;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.servicios.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {  //metodo registro formulario
        return "cliente_registrar.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String genero, @RequestParam String apellidoNombre, @RequestParam String relacion, @RequestParam String dni, @RequestParam String tel, @RequestParam String cp, @RequestParam String domicilio, @RequestParam String localidad,/*@RequestParam MultipartFile archivo,*/ ModelMap modelo, @RequestParam String provincia, @RequestParam String estadoCivil, @RequestParam String fechaNacimiento) {
        try {
            clienteServicio.crearCliente(genero, apellidoNombre, relacion, dni, tel, cp, domicilio, localidad, provincia, estadoCivil, fechaNacimiento);
            modelo.put("exito", "el cliente fue cargado correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "cliente_registrar";
        }
        return "index.html";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo, @Param("palabraClave") String palabraClave) {
        List<Cliente> clientes = clienteServicio.listAll(palabraClave);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("palabraClave", palabraClave);
        return "cliente_lista";
    }

    @GetMapping("/modificar/{idCliente}")
    public String modificar(@PathVariable String idCliente, ModelMap modelo) {

        modelo.put("cliente", clienteServicio.getOne(idCliente));

        return "cliente_modificar.html";
    }

    @PostMapping("/modificar/{idCliente}")
    public String modificar(@PathVariable String idCliente, String genero, String apellidoNombre, String relacion, ModelMap modelo, String dni, String tel, String cp, String domicilio, String localidad, String provincia, String estadoCivil, String fechaNacimiento) {
        try {
            clienteServicio.modificarCLiente(idCliente, genero, apellidoNombre, relacion, dni, tel, cp, domicilio, localidad, provincia, estadoCivil, fechaNacimiento);

            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "cliente_modificar.html"; //siniestro modificar
        }

    }

    /* @GetMapping("/imagen/{idOferta}")  //para devolver imagen como cartas
    public ResponseEntity<byte[]> imagenOferta(@PathVariable String idOferta) {

        Oferta oferta = ofertaServicio.getOne(idOferta);

        byte[] imagen = oferta.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de tipo JPEG

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }*/
    //PARA ELIMINAR
    @GetMapping("/eliminar/{idCliente}")
    public String eliminar(@PathVariable String idCliente, ModelMap modelo) {

        modelo.put("cliente", clienteServicio.getOne(idCliente));
        return "eliminar_cliente.html";
    }

    //PARA ELIMINAR
    @PostMapping("/eliminado/{idCliente}")
    public String eliminado(@PathVariable String idCliente, ModelMap modelo) {

        clienteServicio.borrarPorId(idCliente);

        return "redirect:../lista";
    }

    /* @GetMapping("/exportarPDF")
    public void exportarListadoDeOfertasEnPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Clientes_" + fechaActual + ".pdf";
        response.setHeader(cabecera, valor);

        List<Oferta> ofertas = ofertaServicio.listarOfertas(); //cargo la lista
        OfertaExporterPDF exporter = new OfertaExporterPDF(ofertas);
        exporter.exportar(response);

    }*/
}
