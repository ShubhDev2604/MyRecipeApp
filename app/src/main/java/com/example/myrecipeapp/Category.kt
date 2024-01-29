package com.example.myrecipeapp

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
    )

//Since categories is a list of Category in mealDB API we will make a list
data class CategoriesResponse(val categories: List<Category>)
