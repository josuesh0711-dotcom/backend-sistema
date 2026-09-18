package sistema_inventario.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema_inventario.model.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Long> {
    
    // Método para buscar productos por nombre (ignora mayúsculas/minúsculas y permite coincidencias parciales)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
}