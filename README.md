Caso de Estudio: Librería — Programación III (Parcial 1) - Willy Salinas


Checkpoint 1: Diseño de Clases

1. Clase abstracta `ProductoLibreria`
- **Atributos:** `id: Int`, `titulo: String`, `precio: Double`, `stock: Int`
- **Métodos:**
  - `hayStock(): Boolean`: indica si `stock > 0`.
  - `describir(): String` *(abstracto)*: devuelve la descripción general del producto.

2. Subclase `Libro` (hereda de `ProductoLibreria`)
- **Atributos propios:** `autor: String`, `paginas: Int`
- **Métodos:**
  - `esLecturaLarga(): Boolean`: retorna `true` si `paginas >= 300`.
  - `describir(): String` *(override)*: muestra título, autor, páginas, si es lectura larga o corta y precio.

3. Subclase `Revista` (hereda de `ProductoLibreria`)
- **Atributos propios:** `numeroEdicion: Int`, `periodicidad: String`
- **Métodos:**
  - `etiquetaEdicion(): String`: retorna el formato de edición y periodicidad.
  - `describir(): String` *(override)*: muestra título, edición, periodicidad y precio.

4. Clase `CatalogoLibreria` (relación de agregación)
- **Atributos:** `nombreTienda: String`, `items: MutableList<ProductoLibreria>`
- **Relación:** agrupa múltiples instancias de `ProductoLibreria` (`Libro` y `Revista`).
- **Métodos:**
  - `agregarItem(item: ProductoLibreria)`
  - `obtenerLibros(): List<Libro>`
  - `obtenerRevistas(): List<Revista>`
  - `datosIniciales(): CatalogoLibreria`

Diseño de Interfaz (Jetpack Compose)

Pantalla 1: Catálogo de Librería (`CatalogoScreen`)
- **Objetivo:** Mostrar la lista de productos disponibles (`Libro` y `Revista`) con su tipo, título y precio.
- **Componentes de Jetpack Compose:** `Scaffold`, `TopAppBar`, `LazyColumn`, `Card`, `Column`, `Row`, `Text`, `Badge` o `AssistChip`, y `Button` para ver detalle o cambiar de pantalla.

Pantalla 2: Detalle y Disponibilidad (`DetalleProductoScreen`)
- **Objetivo:** Mostrar los datos específicos del elemento seleccionado (por ejemplo, autor/ISBN si es `Libro`, o edición/periodicidad si es `Revista`) y permitir alternar o consultar disponibilidad.
- **Componentes de Jetpack Compose:** `Scaffold`, `TopAppBar`, `Column`, `Card`, `Text`, `HorizontalDivider`, `Button` / `OutlinedButton`, e `IconButton` para volver al catálogo.