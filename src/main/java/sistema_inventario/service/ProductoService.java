package sistema_inventario.service;

import java.util.List;
import java.util.Optional; 
import org.springframework.stereotype.Service;

import sistema_inventario.model.Producto;
import sistema_inventario.repository.ProductosRepository;

@Service
public class ProductoService {
    
    private final ProductosRepository repository;

    public ProductoService(ProductosRepository repository) {
        this.repository = repository;
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return repository.save(producto);
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminarProducto(Long id) {
        repository.deleteById(id); 
    } 

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return repository.findById(id).map(producto -> {
            producto.setCodigo(productoActualizado.getCodigo());
            producto.setNombre(productoActualizado.getNombre());
            producto.setMarca(productoActualizado.getMarca()); // 👈 Actualización de la Marca añadida aquí
            producto.setCategoria(productoActualizado.getCantidad() != null ? productoActualizado.getCategoria() : producto.getCategoria()); // Mantiene estructura
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setCantidad(productoActualizado.getCantidad());
            return repository.save(producto);
        }).orElse(null);
    }
}