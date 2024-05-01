package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Cliente;
import com.ipfs.web.entidades.Conductor;
import com.ipfs.web.entidades.Siniestro;
import com.ipfs.web.entidades.Vehiculo;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.servicios.ClienteServicio;
import com.ipfs.web.servicios.ConductorServicio;
import com.ipfs.web.servicios.RelevamientoServicio;
import com.ipfs.web.servicios.SiniestroServicio;
import com.ipfs.web.servicios.VehiculoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/relevamiento")
public class RelevamientoControlador {

    @Autowired
    private RelevamientoServicio relevamientoServicio;
    @Autowired
    private VehiculoServicio vehiculoServicio;
    @Autowired
    private SiniestroServicio siniestroServicio;
    @Autowired
    private ConductorServicio conductorServicio;
    @Autowired
    private ClienteServicio clienteServicio; 


    @GetMapping("/registrar") //localhost:8080/relevamiento/registrar
    public String registrar(ModelMap modelo) {
        List<Cliente> clientes = clienteServicio.listarCliente();
        List<Vehiculo> vehiculos = vehiculoServicio.listarVehiculos();
        List<Siniestro> siniestros = siniestroServicio.listarSiniestro();
        List<Conductor> conductores = conductorServicio.listarConductor();
    
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("siniestros", siniestros);
        modelo.addAttribute("conductores", conductores);
    
        return "relevamiento_registrar.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam("agenciaSeguro") String agenciaSeguro,
                             @RequestParam("idRelevamiento") String idRelevamiento,
                          @RequestParam("idConductor") String idConductor,
                          @RequestParam("idCliente") String idCliente,
                          @RequestParam("idSiniestro") String idSiniestro,
                          @RequestParam("idVehiculo") String idVehiculo,
                          ModelMap modelo) {
        try {
            relevamientoServicio.crearRelevamiento(idRelevamiento, idConductor, idVehiculo, idCliente, idSiniestro, agenciaSeguro);
            modelo.put("exito", "El relevamiento fue cargado correctamente!");
        } catch (MiException ex) {
            List<Cliente> clientes = clienteServicio.listarCliente();
            List<Conductor> conductores = conductorServicio.listarConductor();
            List<Vehiculo> vehiculos = vehiculoServicio.listarVehiculos();
            List<Siniestro> siniestros = siniestroServicio.listarSiniestro();
    
            modelo.addAttribute("clientes", clientes);
            modelo.addAttribute("conductores", conductores);
            modelo.addAttribute("vehiculos", vehiculos);
            modelo.addAttribute("siniestros", siniestros);
            modelo.put("error", ex.getMessage());
    
            return "relevamiento_registrar.html";
        }
        return "index.html";
    }
    
}
    //PREGUNTAR  sobre la lista de productos productos productos 
   /* @GetMapping("/lista")
    public String listar(ModelMap modelo, @Param("palabraClave") String palabraClave) {
        List<Producto> productos = productoServicio.listAll(palabraClave);
        modelo.addAttribute("productos", productos);
        modelo.addAttribute("palabraClave", palabraClave);
        return "producto_list";
    }*/

    /*@GetMapping("/modificar/{idProducto}")
    public String modificar(@PathVariable String idProducto, ModelMap modelo) {

        modelo.put("producto", productoServicio.getOne(idProducto));

        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        List<Rubro> rubros = rubroServicio.listarRubros();

        modelo.addAttribute("proveedores", proveedores);
        modelo.addAttribute("rubros", rubros);

        return "producto_modificar.html";
    }*/

   /*  @PostMapping("/modificar/{idProducto}")
    public String modificar(@PathVariable String idProducto, MultipartFile archivo, String codigo, String nombre, Integer precio, String idProveedor, String idRubro, ModelMap modelo) {
        try {
            List<Proveedor> proveedores = proveedorServicio.listarProveedores();
            List<Rubro> rubros = rubroServicio.listarRubros();

            modelo.addAttribute("proveedores", proveedores);
            modelo.addAttribute("rubros", rubros);

            productoServicio.modificarProducto(archivo, idProducto, codigo, nombre, precio, idProveedor, idRubro);

            return "redirect:../lista";

        } catch (MiException ex) {
            List<Proveedor> proveedores = proveedorServicio.listarProveedores();
            List<Rubro> rubros = rubroServicio.listarRubros();

            modelo.put("error", ex.getMessage());

            modelo.addAttribute("proveedores", proveedores);
            modelo.addAttribute("rubros", rubros);

            return "producto_modificar.html";
        }

    }

    @GetMapping("/imagen/{idProducto}")  //para devolver imagen como cartas
    public ResponseEntity<byte[]> imagenProducto(@PathVariable String idProducto) {

        Producto producto = productoServicio.getOne(idProducto);

        byte[] imagen = producto.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG); //se va a recibir una imagen de tipo JPEG

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

    //PARA ELIMINAR
    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(@PathVariable String idProducto, ModelMap modelo) {

        modelo.put("producto", productoServicio.getOne(idProducto));
        return "eliminar_producto.html";
    }

    //PARA ELIMINAR
    @PostMapping("/eliminado/{idProducto}")
    public String eliminado(@PathVariable String idProducto, ModelMap modelo) {

        productoServicio.borrarPorId(idProducto);

        return "redirect:../lista";
    }

    //PARA REPORTES PDF  DE PRODUCTOS
    @GetMapping("/exportarPDF")
    public void exportarListadoDeProductosEnPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Clientes_" + fechaActual + ".pdf";
        response.setHeader(cabecera, valor);

        List<Producto> productos = productoServicio.listarProductos(); //cargo la lista
        ProductoExporterPDF exporter = new ProductoExporterPDF(productos);
        exporter.exportar(response);
    }*/ 

