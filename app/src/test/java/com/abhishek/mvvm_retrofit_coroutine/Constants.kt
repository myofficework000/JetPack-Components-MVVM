package com.abhishek.mvvm_retrofit_coroutine

object Constants {
    const val CATEGORY_FAILURE_RESPONSE = """{
	"status" : 1,
	"message" : "Failed to load data",
	"categories" : [
	]
}"""

    const val CATEGORY_SUCCESS_RESPONSE = """
    {
    "status": 0,
    "message": "Success",
    "categories": [
        {
            "category_id": "1",
            "category_name": "Smart Phones",
            "category_image_url": "smartphones.png",
            "is_active": "1"
        },
        {
            "category_id": "2",
            "category_name": "Laptops",
            "category_image_url": "laptops.png",
            "is_active": "1"
        },
        {
            "category_id": "3",
            "category_name": "Men's Fashion",
            "category_image_url": "mensfashion.png",
            "is_active": "1"
        },
        {
            "category_id": "4",
            "category_name": "Women's Fashion",
            "category_image_url": "womensfashion.png",
            "is_active": "1"
        },
        {
            "category_id": "5",
            "category_name": "Kids Fashion",
            "category_image_url": "kidsfashion.png",
            "is_active": "1"
        },
        {
            "category_id": "6",
            "category_name": "Home Appliances",
            "category_image_url": "homeappliances.png",
            "is_active": "1"
        },
        {
            "category_id": "7",
            "category_name": "Grocery",
            "category_image_url": "grocery.png",
            "is_active": "1"
        },
        {
            "category_id": "8",
            "category_name": "Beauty & Cosmetics",
            "category_image_url": "beauty_and_cosmetics.png",
            "is_active": "1"
        }
    ]
}
    """
}