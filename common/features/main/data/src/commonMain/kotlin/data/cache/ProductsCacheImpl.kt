package data.cache

import data.cache.models.toProductDTO
import data.remote.models.RemoteProduct
import domain.models.ProductDTO
import ru.gozerov.database.AppDatabase

class ProductsCacheImpl(
    private val appDatabase: AppDatabase
) : ProductsCache {

    override suspend fun saveProducts(products: List<RemoteProduct>) {
        appDatabase.transaction {
            appDatabase.productsQueries.clearTable()

            products.forEach { product ->
                appDatabase.productsQueries.insertProduct(
                    id = product.id.toLong(),
                    name = product.title,
                    description = product.description,
                    image = product.image,
                    price = product.price,
                    isInCart = false,
                    isInFavorites = false
                )
            }
        }
    }

    override suspend fun getProducts(): List<ProductDTO> = appDatabase.productsQueries.getProducts()
        .executeAsList()
        .map { product -> product.toProductDTO() }

}