import java.io.*;
import java.util.*;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
        cargarProductos();
    }

    private void cargarProductos() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\productos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Producto producto = new Producto(datos[0], datos[1], datos[2],
                        Double.parseDouble(datos[3]), Integer.parseInt(datos[4]));
                productos.add(producto);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        guardarProductos(); // Asegurar que los cambios se reflejan en el archivo
    }

    public void actualizarProducto(String id, Producto nuevoProducto) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                producto.setNombre(nuevoProducto.getNombre());
                producto.setCategoria(nuevoProducto.getCategoria());
                producto.setPrecio(nuevoProducto.getPrecio());
                producto.setCantidadDisponible(nuevoProducto.getCantidadDisponible());
                guardarProductos(); // Reflejar los cambios en el archivo
                return;
            }
        }
    }

    public void eliminarProducto(String id) {
        productos.removeIf(producto -> producto.getId().equals(id));
        guardarProductos(); // Reflejar los cambios en el archivo
    }

    public Producto buscarProductoPorId(String id) {
        return productos.stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Producto> buscarPorNombre(String nombre) {//Metodo para buscar por nombre
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    public Producto obtenerProductoMasCaro() {
        return productos.stream()
                .max(Comparator.comparing(Producto::getPrecio))
                .orElse(null);
    }

    public int contarProductosPorCategoria(String categoria) {
        return (int) productos.stream()
                .filter(producto -> producto.getCategoria().equalsIgnoreCase(categoria))
                .count();
    }

    public static void generarReporte() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\reporte_inventario.txt"))) {
            writer.write(String.format("%-18s | %-18s | %-18s | %-18s | %-22s | %-18s\n",
                    "ID", "Producto", "Categoría", "Precio", "Unidades Totales", "Valor Total"));

            try (Scanner scanner = new Scanner(new File("D:\\productos.txt"))) {
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] campos = linea.split(",");

                    String idProducto = campos[0];
                    String nombreProducto = campos[1];
                    String categoria = campos[2];
                    double precio = Double.parseDouble(campos[3]);
                    int cantidadDisponible = Integer.parseInt(campos[4]);

                    double valorProducto = precio * cantidadDisponible;

                    writer.write(String.format("%-18s | %-18s | %-18s | $%-17.2f | %-22d | $%-18.2f\n",
                            idProducto, nombreProducto, categoria, precio, cantidadDisponible, valorProducto));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al crear el informe: " + e.getMessage());
        }

    }

    private void guardarProductos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\productos.txt"))) {
            for (Producto producto : productos) {
                bw.write(producto.getId() + "," + producto.getNombre() + "," +
                        producto.getCategoria() + "," + producto.getPrecio() + "," +
                        producto.getCantidadDisponible());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }
}
