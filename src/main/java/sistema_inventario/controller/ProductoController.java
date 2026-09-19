package sistema_inventario.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import sistema_inventario.model.Producto;
import sistema_inventario.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "https://inventariojosue.netlify.app")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        List<Producto> lista = service.listarProductos();
        
        System.out.println("\n==================================================");
        System.out.println("📦 PRODUCTOS CONSULTADOS (Total: " + lista.size() + ")");
        for (Producto p : lista) {
            System.out.println(" > ID: " + p.getId() + " | Código: " + p.getCodigo() + " | Nombre: " + p.getNombre());
        }
        System.out.println("==================================================\n");

        return lista;
    }

    @GetMapping("/{id}")
    public Producto buscarPorId(@PathVariable Long id) {
        Producto producto = service.buscarPorId(id).orElse(null);
        if (producto != null) {
            System.out.println("\n🔍 Producto consultado por ID " + id + ": " + producto.getNombre() + "\n");
        }
        return producto;
    }
@PostMapping
    public Producto registrarProducto(@RequestBody Producto producto) {
        System.out.println("\n💾 Registrando nuevo producto: " + producto.getNombre() + "\n");
        return service.guardarProducto(producto);
  
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {
        System.out.println("\n✏️ Actualizando producto con ID " + id + "\n");
        return service.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
        System.out.println("\n🗑️ Producto con ID " + id + " eliminado.\n");
    }
}