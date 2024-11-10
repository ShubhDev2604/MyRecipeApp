package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
    ): Parcelable

//Since categories is a list of Category in mealDB API we will make a list
data class CategoriesResponse(val categories: List<Category>)
