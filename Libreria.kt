abstract class ProductoLibreria(
    val id: Int,
    val titulo: String,
    val precio: Double,
    val stock: Int
) {
    fun hayStock(): Boolean = stock > 0
    abstract fun describir(): String
}

class Libro(
    id: Int,
    titulo: String,
    precio: Double,
    stock: Int,
    val autor: String,
    val paginas: Int
) : ProductoLibreria(id, titulo, precio, stock) {

    fun esLecturaLarga(): Boolean = paginas >= 300

    override fun describir(): String {
        val duracion = if (esLecturaLarga()) "Extenso" else "Corto"
        return "$titulo — $autor ($paginas págs., $duracion) | Bs. $precio"
    }
}

class Revista(
    id: Int,
    titulo: String,
    precio: Double,
    stock: Int,
    val numeroEdicion: Int,
    val periodicidad: String
) : ProductoLibreria(id, titulo, precio, stock) {

    fun etiquetaEdicion(): String = "Ed. #$numeroEdicion ($periodicidad)"

    override fun describir(): String {
        return "$titulo — ${etiquetaEdicion()} | Bs. $precio"
    }
}

class CatalogoLibreria(
    val nombreTienda: String,
    private val items: MutableList<ProductoLibreria> = mutableListOf()
) {
    fun agregarItem(item: ProductoLibreria) {
        items.add(item)
    }

    fun obtenerLibros(): List<Libro> = items.filterIsInstance<Libro>()

    fun obtenerRevistas(): List<Revista> = items.filterIsInstance<Revista>()

    fun datosIniciales(): CatalogoLibreria {
        agregarItem(Libro(1, "Código Limpio", 145.0, 6, "Robert C. Martin", 464))
        agregarItem(Libro(2, "Estructuras y Algoritmos", 120.0, 4, "A. V. Aho", 380))
        agregarItem(Revista(3, "Ciencia y Tecnología Hoy", 35.0, 10, 48, "Mensual"))
        agregarItem(Revista(4, "Cultura y Letras", 28.0, 5, 19, "Semanal"))
        return this
    }
}