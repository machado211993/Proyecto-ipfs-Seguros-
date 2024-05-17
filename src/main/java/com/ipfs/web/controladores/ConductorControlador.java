package com.ipfs.web.controladores;


import com.ipfs.web.entidades.Conductor;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.servicios.ConductorServicio;
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
@RequestMapping("/conductor")
public class ConductorControlador {

    @Autowired
    private ConductorServicio conductorServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {  //metodo registro formulario
        return "conductor_registrar.html";

    }

    @PostMapping("/registro") //metodo registrado 
    public String registro(@RequestParam(required = false) String genero, @RequestParam String titular, @RequestParam String nombreConductor, @RequestParam String dni, @RequestParam String profesion, @RequestParam String tel, @RequestParam String domicilio, @RequestParam String cp, @RequestParam String localidad, @RequestParam String provincia, @RequestParam String estadoCivil, @RequestParam String fechaNacimiento, @RequestParam String testAlcoholemia, @RequestParam String nroRegistro, @RequestParam String categoria, @RequestParam String Expedido, @RequestParam String vencimiento,/*@RequestParam MultipartFile archivo,*/ ModelMap modelo) {
        try {
            conductorServicio.crearConductor(vencimiento, genero, titular, nombreConductor, dni, profesion, tel, domicilio, cp, localidad, provincia, estadoCivil, fechaNacimiento, testAlcoholemia, nroRegistro, categoria, Expedido);
            modelo.put("exito", "el conductor fue cargado correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "conductor_registrar";
        }
        return "index.html";

    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo, @Param("palabraClave") String palabraClave) {
        List<Conductor> conductores = conductorServicio.listAll(palabraClave);
        modelo.addAttribute("conductores", conductores);
        modelo.addAttribute("palabraClave", palabraClave);
        return "conductor_lista";
    }

    @GetMapping("/modificar/{idConductor}")
    public String modificar(@PathVariable String idConductor, ModelMap modelo) {

        modelo.put("conductor", conductorServicio.getOne(idConductor));

        return "conductor_modificar.html";
    }

    @PostMapping("/modificar/{idConductor}")
    public String modificar(@PathVariable String idConductor, String genero, String titular, ModelMap modelo, String apellidoNombre, String dni, String profesion, String tel, String domicilio, String cp, String localidad, String provincia, String estadoCivil, String fechaNacimiento, String testAlcoholemia, String nroRegistro, String categoria, String Expedido, String vencimiento) {
        try {
            conductorServicio.modificarConductor(idConductor, genero, titular, apellidoNombre, dni, profesion, tel, domicilio, cp, localidad, provincia, estadoCivil, fechaNacimiento, testAlcoholemia, nroRegistro, categoria, Expedido, vencimiento);

            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "conductor_modificar.html"; //conductor modificar
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
    @GetMapping("/eliminar/{idConductor}")
    public String eliminar(@PathVariable String idConductor, ModelMap modelo) {

        modelo.put("conductor", conductorServicio.getOne(idConductor));
        return "conductor_eliminar.html";
    }

    //PARA ELIMINAR
    @PostMapping("/eliminado/{idConductor}")
    public String eliminado(@PathVariable String idConductor, ModelMap modelo) {

        conductorServicio.borrarPorId(idConductor);

        return "redirect:../listar";
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
