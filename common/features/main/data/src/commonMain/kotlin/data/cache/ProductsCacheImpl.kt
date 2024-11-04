package data.cache

import data.cache.models.toProductDTO
import data.remote.models.RemoteProduct
import domain.models.ProductDTO
import ru.gozerov.database.AppDatabase

internal class ProductsCacheImpl(
    private val appDatabase: AppDatabase
) : ProductsCache {

    override suspend fun saveProducts(products: List<RemoteProduct>) {
        appDatabase.transaction {
            appDatabase.productsQueries.deleteOldProducts()
            val cachedIds =
                appDatabase.productsQueries.getProducts().executeAsList().map { it.id.toInt() }
            val productsToInsert = products.filter { dto -> dto.id !in cachedIds }
            println(productsToInsert.toString())

            productsToInsert.forEach { product ->
                appDatabase.productsQueries.insertProduct(
                    id = product.id.toLong(),
                    name = product.title,
                    description = product.description,
                    image = product.image,
                    price = product.price
                )
            }
        }
    }

    override suspend fun getProducts(): List<ProductDTO> =
        appDatabase.productsQueries.ProductWithFlags()
            .executeAsList().map { product -> product.toProductDTO() }

    override suspend fun updateCart(productId: Int, isAdding: Boolean) {
        if (isAdding)
            appDatabase.cartQueries.insertIntoCart(productId.toLong(), 1L)
        else
            appDatabase.cartQueries.removeFromCart(productId.toLong())
    }

    override suspend fun updateShoppingList(productId: Int, isAdding: Boolean) {
        if (isAdding)
            appDatabase.shopping_listQueries.insertIntoShoppingList(productId.toLong())
        else
            appDatabase.shopping_listQueries.removeFromShoppingList(productId.toLong())
    }

}