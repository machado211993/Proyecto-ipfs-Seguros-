package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Siniestro;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.servicios.SiniestroServicio;
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
@RequestMapping("/siniestro")
public class SiniestroControlador {

    @Autowired
    private SiniestroServicio siniestroServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {  //metodo registro formulario
        return "siniestro_registrar.html";

    }

    @PostMapping("/registro") //metodo registrado 
    public String registro(@RequestParam(required = false) String polizaNumero, @RequestParam String numeroSiniestro, @RequestParam String fechaSiniestro, @RequestParam String horaSiniestro, @RequestParam String lugarHecho, @RequestParam String estadoTiempo, @RequestParam String comisaria, @RequestParam String provincia,/*@RequestParam MultipartFile archivo,*/ ModelMap modelo) {
        try {
            siniestroServicio.crearSiniestro(horaSiniestro, polizaNumero, numeroSiniestro, fechaSiniestro, horaSiniestro, lugarHecho, estadoTiempo, comisaria);
            modelo.put("exito", "el siniestro fue cargado correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "siniestro_registrar";
        }
        return "index.html";

    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo, @Param("palabraClave") String palabraClave) {
        List<Siniestro> siniestros = siniestroServicio.listAll(palabraClave);
        modelo.addAttribute("siniestros", siniestros);
        modelo.addAttribute("palabraClave", palabraClave);
        return "siniestro_lista";
    }

    @GetMapping("/modificar/{idSiniestro}")
    public String modificar(@PathVariable String idSiniestro, ModelMap modelo) {

        modelo.put("siniestro", siniestroServicio.getOne(idSiniestro));

        return "siniestro_modificar.html";
    }

    @PostMapping("/modificar/{idSiniestro}")
    public String modificar(@PathVariable String idSiniestro, String polizaNumero, String numeroSiniestro, String fechaSiniestro, ModelMap modelo, String horaSiniestro, String lugarHecho, String estadoTiempo, String comisaria, String provincia) {
        try {
            siniestroServicio.modificarSiniestro(idSiniestro, polizaNumero, numeroSiniestro, fechaSiniestro, horaSiniestro, lugarHecho, estadoTiempo, comisaria, provincia);

            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "siniestro_modificar.html"; //siniestro modificar
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
    @GetMapping("/eliminar/{idSiniestro}")
    public String eliminar(@PathVariable String idSiniestro, ModelMap modelo) {

        modelo.put("siniestro", siniestroServicio.getOne(idSiniestro));
        return "eliminar_siniestro.html";
    }

    //PARA ELIMINAR
    @PostMapping("/eliminado/{idSiniestro}")
    public String eliminado(@PathVariable String idSiniestro, ModelMap modelo) {

        siniestroServicio.borrarPorId(idSiniestro);

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
