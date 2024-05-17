package com.ipfs.web.controladores;


import com.ipfs.web.entidades.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.servicios.VehiculoServicio;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/vehiculo")
public class VehiculoControlador {

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {  //metodo registro formulario
        return "vehiculo_registrar.html";

    }

    @PostMapping("/registro") //metodo registrado 
    public String registro(@RequestParam(required = false) String aseguradora, @RequestParam String tipo, @RequestParam String anio, @RequestParam String lesiones, @RequestParam String danosMateriales, @RequestParam String color, @RequestParam String muerte, @RequestParam String dominio, @RequestParam String marca, @RequestParam String modeloAuto,/*@RequestParam MultipartFile archivo,*/ ModelMap modelo) {
        try {
            vehiculoServicio.crearVehiculo(aseguradora, dominio, marca, modeloAuto, tipo, color, anio, danosMateriales, lesiones, muerte);
            modelo.put("exito", "el vehiculo fue cargado correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "vehiculo_registrar";
        }
        return "index.html";

    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo, @Param("palabraClave") String palabraClave) {
        List<Vehiculo> vehiculos = vehiculoServicio.listAll(palabraClave);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("palabraClave", palabraClave);
        return "vehiculo_lista";
    }


    @GetMapping("/modificar/{idVehiculo}")
    public String modificar(@PathVariable String idVehiculo, ModelMap modelo) {

        modelo.put("vehiculo", vehiculoServicio.getOne(idVehiculo));

        return "vehiculo_modificar.html";
    }

    @PostMapping("/modificar/{idOferta}")
    public String modificar(@PathVariable String idVehiculo, String aseguradora, String anio, String modelo, ModelMap modelo2, String tipo, String lesiones, String danosMateriales, String color, String muerte) {
        try {
            vehiculoServicio.modificarVehiculo(idVehiculo, aseguradora, tipo, muerte, tipo, color, anio, danosMateriales, lesiones, muerte, modelo);

            return "redirect:../lista";
        } catch (MiException ex) {
            modelo2.put("error", ex.getMessage());
            return "vehiculo_modificar.html"; //oferta modificar
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
    @GetMapping("/eliminar/{idVehiculo}")
    public String eliminar(@PathVariable String idVehiculo, ModelMap modelo) {

        modelo.put("vehiculo", vehiculoServicio.getOne(idVehiculo));
        return "vehiculo_eliminar.html";
    }

    //PARA ELIMINAR
    @PostMapping("/eliminado/{idVehiculo}")
    public String eliminado(@PathVariable String idVehiculo, ModelMap modelo) {

        vehiculoServicio.borrarPorId(idVehiculo);

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
