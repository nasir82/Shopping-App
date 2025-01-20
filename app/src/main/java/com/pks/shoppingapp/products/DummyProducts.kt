package com.pks.shoppingapp.products

import com.pks.shoppingapp.home.domain.model.ProductAttributeModel
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.domain.model.ProductVariationsModel

val hardcodedProducts = listOf(
    ProductModel(
        id = "P1",
        stock = 100,
        sku = "SKU1",
        price = 29.99,
        title = "Casual Shirt",
        salePrice = 24.99,
        thumbnail = "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fshirt3.jpg?alt=media&token=fe7a0d18-7f34-4f41-89de-49a13fbb947f",
        description = "A comfortable and stylish casual shirt.",
        categoryId = "C1",
        images = listOf(
            "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fshirt2.jpg?alt=media&token=dc3b4066-a688-4737-bf4b-c0aeef83891d",
            "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fshirt1.jpg?alt=media&token=dc2ac9fc-7490-4088-a0ab-e36414a0ddc5"
        ),
        productAttribute = listOf(
            ProductAttributeModel(name = "Size", values = listOf("S", "M", "L", "XL", "XXL"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "V1",
                sku = "SKU1_S_Red",
                image = "https://via.placeholder.com/150/FF5733",
                description = "Size S, Red Color",
                price = 29.99,
                salePrice = 24.99,
                stock = 20,
                attributeValues = mapOf("Size" to "S")
            ),
            ProductVariationsModel(
                id = "V2",
                sku = "SKU1_M_Black",
                image = "https://via.placeholder.com/150/3357FF",
                description = "Size M, Black Color",
                price = 29.99,
                salePrice = 24.99,
                stock = 15,
                attributeValues = mapOf("Size" to "M")
            ),
            ProductVariationsModel(
                id = "V1",
                sku = "SKU1_S_Red",
                image = "https://via.placeholder.com/150/FF5733",
                description = "Size S, Red Color",
                price = 29.99,
                salePrice = 24.99,
                stock = 20,
                attributeValues = mapOf("Size" to "L")
            ),
            ProductVariationsModel(
                id = "V2",
                sku = "SKU1_M_Black",
                image = "https://via.placeholder.com/150/3357FF",
                description = "Size M, Black Color",
                price = 29.99,
                salePrice = 24.99,
                stock = 15,
                attributeValues = mapOf("Size" to "XL")
            ),
            ProductVariationsModel(
                id = "V2",
                sku = "SKU1_M_Black",
                image = "https://via.placeholder.com/150/3357FF",
                description = "Size M, Black Color",
                price = 29.99,
                salePrice = 24.99,
                stock = 15,
                attributeValues = mapOf("Size" to "XXL")
            )
        )
    ),
    ProductModel(
        id = "P2",
        stock = 120,
        sku = "SKU2",
        price = 49.99,
        title = "Denim Jeans",
        salePrice = 39.99,
        thumbnail = "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fblack_denim.jpg?alt=media&token=4265dd4a-99e8-4d08-a48f-f859c27d4069",
        description = "Classic denim jeans for everyday use.",
        categoryId = "C2",
        images = listOf(
            "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fblack_denim.jpg?alt=media&token=4265dd4a-99e8-4d08-a48f-f859c27d4069",
            "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fblue_denim.jpg?alt=media&token=fd448f32-9555-4ba0-a9ec-18bbdc57e5e9"
        ),
        productAttribute = listOf(
            ProductAttributeModel(name = "Size", values = listOf("S", "M", "L", "XL", "XXL")),
            ProductAttributeModel(name = "Color", values = listOf("Black", "Red", "Black", "White"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "V3",
                sku = "SKU2_XL_White",
                image = "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fblue_denim.jpg?alt=media&token=fd448f32-9555-4ba0-a9ec-18bbdc57e5e9",
                description = "Size XL, White Color",
                price = 49.99,
                salePrice = 39.99,
                stock = 30,
                attributeValues = mapOf("Size" to "XL", "Color" to "White")
            ),
            ProductVariationsModel(
                id = "V4",
                sku = "SKU2_L_Green",
                image = "https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fblack_denim.jpg?alt=media&token=4265dd4a-99e8-4d08-a48f-f859c27d4069\n",
                description = "Size L, Green Color",
                price = 49.99,
                salePrice = 39.99,
                stock = 25,
                attributeValues = mapOf("Size" to "L", "Color" to "Black")
            )
        )
    ),
    ProductModel(
        id = "P3",
        stock = 80,
        sku = "SKU3",
        price = 19.99,
        title = "Graphic T-Shirt",
        salePrice = 14.99,
        thumbnail = "https://via.placeholder.com/150/FFFF33",
        description = "Trendy graphic t-shirt for casual wear.",
        categoryId = "C3",
        images = listOf(
            "https://via.placeholder.com/150/FFFF33",
            "https://via.placeholder.com/150/FF33FF",
            "https://via.placeholder.com/150/33FF57"
        ),
        productAttribute = listOf(
            ProductAttributeModel(name = "Size", values = listOf("S", "M", "L", "XL", "XXL")),
            ProductAttributeModel(name = "Color", values = listOf("Green", "Red", "Black", "White"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "V5",
                sku = "SKU3_M_Red",
                image = "https://via.placeholder.com/150/FF33FF",
                description = "Size M, Red Color",
                price = 19.99,
                salePrice = 14.99,
                stock = 40,
                attributeValues = mapOf("Size" to "M", "Color" to "Red")
            ),
            ProductVariationsModel(
                id = "V6",
                sku = "SKU3_S_Black",
                image = "https://via.placeholder.com/150/3357FF",
                description = "Size S, Black Color",
                price = 19.99,
                salePrice = 14.99,
                stock = 18,
                attributeValues = mapOf("Size" to "S", "Color" to "Black")
            )
        )
    ),
    ProductModel(
        id = "P4",
        stock = 150,
        sku = "SKU4",
        price = 59.99,
        title = "Winter Jacket",
        salePrice = 49.99,
        thumbnail = "https://via.placeholder.com/150/FF33FF",
        description = "Warm winter jacket for cold days.",
        categoryId = "C4",
        images = listOf(
            "https://via.placeholder.com/150/FF33FF",
            "https://via.placeholder.com/150/33FF57",
            "https://via.placeholder.com/150/FFFF33"
        ),
        productAttribute = listOf(
            ProductAttributeModel(name = "Size", values = listOf("S", "M", "L", "XL", "XXL")),
            ProductAttributeModel(name = "Color", values = listOf("Green", "Red", "Black", "White"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "V7",
                sku = "SKU4_XXL_Green",
                image = "https://via.placeholder.com/150/33FF57",
                description = "Size XXL, Green Color",
                price = 59.99,
                salePrice = 49.99,
                stock = 20,
                attributeValues = mapOf("Size" to "XXL", "Color" to "Green")
            ),
            ProductVariationsModel(
                id = "V8",
                sku = "SKU4_L_White",
                image = "https://via.placeholder.com/150/FFFF33",
                description = "Size L, White Color",
                price = 59.99,
                salePrice = 49.99,
                stock = 22,
                attributeValues = mapOf("Size" to "L", "Color" to "White")
            )
        )
    ),
    ProductModel(
        id = "P5",
        stock = 50,
        sku = "SKU5",
        price = 15.99,
        title = "Cotton Socks",
        salePrice = 9.99,
        thumbnail = "https://via.placeholder.com/150/3357FF",
        description = "Soft and breathable cotton socks.",
        categoryId = "C5",
        images = listOf(
            "https://via.placeholder.com/150/3357FF",
            "https://via.placeholder.com/150/FF33FF",
            "https://via.placeholder.com/150/FFFF33"
        ),
        productAttribute = listOf(
            ProductAttributeModel(name = "Size", values = listOf("S", "M", "L", "XL", "XXL")),
            ProductAttributeModel(name = "Color", values = listOf("Green", "Red", "Black", "White"))
        ),
        productVariation = listOf(
            ProductVariationsModel(
                id = "V9",
                sku = "SKU5_S_Green",
                image = "https://via.placeholder.com/150/33FF57",
                description = "Size S, Green Color",
                price = 15.99,
                salePrice = 9.99,
                stock = 10,
                attributeValues = mapOf("Size" to "S", "Color" to "Green")
            ),
            ProductVariationsModel(
                id = "V10",
                sku = "SKU5_M_Red",
                image = "https://via.placeholder.com/150/FF33FF",
                description = "Size M, Red Color",
                price = 15.99,
                salePrice = 9.99,
                stock = 8,
                attributeValues = mapOf("Size" to "M", "Color" to "Red")
            )
        )
    )
)
