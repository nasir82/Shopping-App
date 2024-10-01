package com.pks.shoppingapp.home.domain.model


data class ProductModel(
    var id: String="",
    var stock: Int=0,
    var sku: String="",
    var price: Double=0.0,
    var title: String="",
    var date: String? = null,
    var salePrice: Double=0.0,
    var thumbnail: String="",
    var isFeatured: Boolean = false,
    var brand: BrandModel = BrandModel(),
    var description: String="",
    var categoryId: String="",
    var images: List<String> = mutableListOf(),
    var productType: String="",
    var productAttribute: List<ProductAttributeModel> = mutableListOf(),
    var productVariation: List<ProductVariationsModel> = mutableListOf(),
)
data class BrandModel(

    var id: String="",
    var name: String="",
    var image: String="",
    var isFeatured: Boolean=false,
    var productCount: Int=0
)
data class ProductAttributeModel(
    var name:String="",
    var values:List<String> = emptyList()
)


data class ProductVariationsModel(

    var id:String="",
    var sku: String="",
    var image: String="",
    var description: String="",
    var price: Double=0.0,
    var salePrice: Double=0.0,
    var stock: Int=0,
    var attributeValues:Map<String,String> = mutableMapOf()

)


//val ProductType = object: NavType<ProductModel>(false){
//    override fun get(bundle: Bundle, key: String): ProductModel? {
//        return if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
//            bundle.getParcelable(key,ProductModel::class.java)
//        }else{
//            bundle.getParcelable(key)
//        }
//    }
//
//    override fun parseValue(value: String): ProductModel {
//        return Json.decodeFromString(value)
//    }
//
//    override fun put(bundle: Bundle, key: String, value: ProductModel) {
//        bundle.putParcelable(key,value)
//    }
//
//    override fun serializeAsValue(value: ProductModel): String {
//        return  Json.encodeToString(value)
//    }
//
//}


val products = listOf(
    ProductModel(
        id = "SKU001",
        stock = 50,
        sku = "SKU001",
        price = 19.99,
        title = "Product 1",
        date = System.currentTimeMillis().toString(),
        salePrice = 15.99,
        thumbnail = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fblack-friday-elements-assortment.jpg?alt=media&token=ee071619-613e-42b1-b5bc-4d86343e2d5b",
        isFeatured = true,
        brand = BrandModel(
            id = "brand001",
            name = "Brand 1",
            image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2FScreenshot%202024-09-03%20202847.png?alt=media&token=58999c1f-a34f-47b3-9d44-988e95da68a4",
            isFeatured = true,
            productCount = 100
        ),
        description = "Description of Product 1",
        categoryId = "cat001",
        images = listOf("image1_url", "image2_url"),
        productType = "SINGLE",
        productAttribute = listOf(
            ProductAttributeModel(name = "Color", values = listOf("Red", "Blue", "Green")),
            ProductAttributeModel(name = "Size", values = listOf("S", "M", "L"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "var001",
                sku = "VAR001",
                image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fblack-friday-elements-assortment.jpg?alt=media&token=ee071619-613e-42b1-b5bc-4d86343e2d5b",
                description = "Variation 1 description",
                price = 18.99,
                salePrice = 14.99,
                stock = 30,
                attributeValues = mapOf("Color" to "Red", "Size" to "M")
            )
        )
    ),
    ProductModel(
        id = "SKU002",
        stock = 80,
        sku = "SKU002",
        price = 29.99,
        title = "Product 2",
        date = System.currentTimeMillis().toString(),
        salePrice = 25.99,
        thumbnail = "thumbnail2_url",
        isFeatured = false,
        brand = BrandModel(
            id = "brand002",
            name = "Brand 2",
            image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fcolorful-school-backpack-classroom.jpg?alt=media&token=430e1dde-3cce-49a5-ad80-224997ca3387",
            isFeatured = false,
            productCount = 150
        ),
        description = "Description of Product 2",
        categoryId = "cat002",
        images = listOf(
            "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fblack-friday-elements-assortment.jpg?alt=media&token=ee071619-613e-42b1-b5bc-4d86343e2d5b",
            "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fcolorful-school-backpack-classroom.jpg?alt=media&token=430e1dde-3cce-49a5-ad80-224997ca3387"
        ),
        productType = "VARIABLE",
        productAttribute = listOf(
            ProductAttributeModel(name = "Material", values = listOf("Cotton", "Polyester"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "var002",
                sku = "VAR002",
                image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fcolorful-school-backpack-classroom.jpg?alt=media&token=430e1dde-3cce-49a5-ad80-224997ca3387",
                description = "Variation 2 description",
                price = 27.99,
                salePrice = 23.99,
                stock = 50,
                attributeValues = mapOf("Material" to "Cotton")
            )
        )
    ),
    ProductModel(
        id = "SKU003",
        stock = 40,
        sku = "SKU003",
        price = 15.99,
        title = "Product 3",
        date = System.currentTimeMillis().toString(),
        salePrice = 12.99,
        thumbnail = "thumbnail3_url",
        isFeatured = false,
        brand = BrandModel(
            id = "brand003",
            name = "Brand 3",
            image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fnike.png?alt=media&token=69bcabea-30fd-4a6a-b01f-6c995d79589e",
            isFeatured = false,
            productCount = 80
        ),
        description = "Description of Product 3",
        categoryId = "cat003",
        images = listOf("image5_url", "image6_url"),
        productType = "SINGLE",
        productAttribute = listOf(
            ProductAttributeModel(name = "Color", values = listOf("Yellow", "Black"))
        ),
        productVariation = emptyList()
    ),
    ProductModel(
        id = "SKU004",
        stock = 60,
        sku = "SKU004",
        price = 39.99,
        title = "Product 4",
        date = System.currentTimeMillis().toString(),
        salePrice = 34.99,
        thumbnail = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2FScreenshot%202024-09-03%20202847.png?alt=media&token=58999c1f-a34f-47b3-9d44-988e95da68a4",
        isFeatured = true,
        brand = BrandModel(
            id = "brand004",
            name = "Brand 4",
            image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fnike.png?alt=media&token=69bcabea-30fd-4a6a-b01f-6c995d79589e",
            isFeatured = true,
            productCount = 200
        ),
        description = "Description of Product 4",
        categoryId = "cat004",
        images = listOf("image7_url", "image8_url"),
        productType = "VARIABLE",
        productAttribute = listOf(
            ProductAttributeModel(name = "Size", values = listOf("M", "L", "XL"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "var003",
                sku = "VAR003",
                image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2FScreenshot%202024-09-03%20202847.png?alt=media&token=58999c1f-a34f-47b3-9d44-988e95da68a4",
                description = "Variation 3 description",
                price = 37.99,
                salePrice = 32.99,
                stock = 70,
                attributeValues = mapOf("Size" to "L")
            )
        )
    ),
    ProductModel(
        id = "SKU005",
        stock = 70,
        sku = "SKU005",
        price = 49.99,
        title = "Product 5",
        date = System.currentTimeMillis().toString(),
        salePrice = 44.99,
        thumbnail = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2FScreenshot%202024-09-03%20202847.png?alt=media&token=58999c1f-a34f-47b3-9d44-988e95da68a4",
        isFeatured = true,
        brand = BrandModel(
            id = "brand005",
            name = "Brand 5",
            image = "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fsuit2.png?alt=media&token=232ffc99-2164-4dec-9522-5cb90418a94b",
            isFeatured = true,
            productCount = 300
        ),
        description = "Description of Product 5",
        categoryId = "cat005",
        images = listOf(
            "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2FScreenshot%202024-09-03%20202847.png?alt=media&token=58999c1f-a34f-47b3-9d44-988e95da68a4",
            "https://firebasestorage.googleapis.com/v0/b/firestoreimage-24c12.appspot.com/o/products%2Fsuit2.png?alt=media&token=232ffc99-2164-4dec-9522-5cb90418a94b"
        ),
        productType = "SINGLE",
        productAttribute = listOf(
            ProductAttributeModel(name = "Material", values = listOf("Wool", "Cotton"))
        ),
        productVariation = emptyList()
    )
)





//fun checkStockForCombination(product: ProductModel, color: String, size: String): Int {
//    return product.productVariation.firstOrNull { variation ->
//        variation.attributeValues["Color"] == color && variation.attributeValues["Size"] == size
//    }?.stock ?: 0
//}
//
//// Usage
//val stockForRedM = checkStockForCombination(product, "Red", "M")
//println("Stock for Red M: $stockForRedM")