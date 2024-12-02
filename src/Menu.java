import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("==================================================");
            System.out.println("-          INVENTARIO DE PRODUCTOS               -");
            System.out.println("-       OSCAR DAIRO ROJAS ROA / OROJAS4          -");
            System.out.println("==================================================");
            System.out.println("-     1. Agregar producto                        -");
            System.out.println("-     2. Actualizar producto                     -");
            System.out.println("-     3. Eliminar producto                       -");
            System.out.println("-     4. Buscar por categoría                    -");
            System.out.println("-     5. Generar reporte                         -");
            System.out.println("-     6. Cantidad de productos por categoría     -");
            System.out.println("-     7. Producto más caro                       -");
            System.out.println("-     8. Salir                                   -");
            System.out.println("==================================================");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    String id = scanner.next();
                    System.out.print("Nombre: ");
                    String nombre = scanner.next();
                    System.out.print("Categoría: ");
                    String categoria = scanner.next();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Cantidad disponible: ");
                    int cantidad = scanner.nextInt();
                    inventario.agregarProducto(new Producto(id, nombre, categoria, precio, cantidad));
                    System.out.println("¡Producto agregado exitosamente!");
                    break;

                case 2:
                    System.out.print("Ingrese el ID del producto que desea actualizar: ");
                    String idActualizar = scanner.next();
                    Producto productoExistente = inventario.buscarProductoPorId(idActualizar);
                    if (productoExistente == null) {
                        System.out.println("El producto no existe.");
                    } else {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.next();
                        System.out.print("Nueva categoría: ");
                        String nuevaCategoria = scanner.next();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = scanner.nextDouble();
                        System.out.print("Nueva cantidad: ");
                        int nuevaCantidad = scanner.nextInt();
                        inventario.actualizarProducto(idActualizar, new Producto(idActualizar, nuevoNombre, nuevaCategoria, nuevoPrecio, nuevaCantidad));
                        System.out.println("¡Producto actualizado!");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    String idEliminar = scanner.next();
                    inventario.eliminarProducto(idEliminar);
                    System.out.println("¡Producto eliminado!");
                    break;

                case 4:
                    System.out.println("Seleccione el criterio de búsqueda:");
                    System.out.println("==================================================");
                    System.out.println("-     1. Categoría                               -");
                    System.out.println("-     2. Nombre                                  -");
                    System.out.println("-     3. ID                                      -");
                    System.out.println("==================================================");
                    int criterio = scanner.nextInt();
                    scanner.nextLine();

                    switch (criterio) {
                        case 1:
                            System.out.print("Ingrese categoría: ");
                            String categoriaBuscar = scanner.nextLine();
                            List<Producto> resultadosCategoria = inventario.buscarPorCategoria(categoriaBuscar);
                            if (resultadosCategoria.isEmpty()) {
                                System.out.println("No se encontraron productos en esta categoría.");
                            } else {
                                resultadosCategoria.forEach(p -> System.out.println(p.getNombre()));
                            }
                            break;

                        case 2:
                            System.out.print("Ingrese nombre del producto: ");
                            String nombreBuscar = scanner.nextLine();
                            List<Producto> resultadosNombre = inventario.buscarPorNombre(nombreBuscar);
                            if (resultadosNombre.isEmpty()) {
                                System.out.println("No se encontraron productos con este nombre.");
                            } else {
                                resultadosNombre.forEach(p -> System.out.println(p.getNombre()));
                            }
                            break;

                        case 3:
                            System.out.print("Ingrese ID del producto: ");
                            String idBuscar = scanner.nextLine();
                            Producto productoEncontrado = inventario.buscarProductoPorId(idBuscar);
                            if (productoEncontrado == null) {
                                System.out.println("No se encontró un producto con este ID.");
                            } else {
                                System.out.println("Producto encontrado: " + productoEncontrado.getNombre());
                            }
                            break;

                        default:
                            System.out.println("Opción no válida.");
                    }
                    break;


                case 5:
                    inventario.generarReporte();
                    System.out.println("¡Reporte generado!");
                    break;

                case 6:
                    System.out.print("Indique la categoría: ");
                    String categoriaContar = scanner.next();
                    System.out.println("La cantidad de productos es: " + inventario.contarProductosPorCategoria(categoriaContar));
                    break;

                case 7:
                    Producto masCaro = inventario.obtenerProductoMasCaro();
                    System.out.println("Producto más caro: " + (masCaro != null ? masCaro.getNombre() : "No hay productos"));
                    break;
            }
        } while (opcion != 8);
    }
}

