package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/*
MainViewModel is handling the ViewModel part of the project. It's taking Model or Data from ApiService.kt
and getting it ready for the UI element for the screen to be displayed.
 */
class MainViewModel : ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState
    /*
    _categoriesState is the private state variable used and it will be used locally while categoriesState
    will be used in View maintaining abstraction here
     */

    init{
        fetchCategories()
        /*
        The moment an object of MainViewModel class is made, this function will be called.
         */
    }

    private fun fetchCategories(){
        /*
        This function's primary focus is to fetch or retrieve data from the internet using ApiService.kt
        and loading the data into _categoriesState.
         */
        viewModelScope.launch {
            /*
            viewModelScope.launch is used whenever we use suspend. We know that suspend is of Coroutine
            So suspend gotta be used in a Coroutine Scope, so ViewModelScope.launch is used.
             */
            try{
                /*
                Since loading data from internet depends on a lot of things. So to make the app crash-free
                , we are using try block.
                 */
                val response = recipeService.getCategories()
                /*
                recipeService is a variable or an object which is implementing the ApiService interface.
                So, here we are using creating a variable response of type recipeService and through this we
                are implementing pr using getCategories() which will fetch the data from the php file
                 */

                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
                /*
                getCategories() is of returntype data class CategoryResponse which contains of List of Category
                so we are copying the list in the list we made in RecipeState, and setting the loading to be false
                and error to be null as the data we want is being copied.
                 */
            }catch(e: Exception){
                /*
                If we get any error catch block will be implemented
                 */
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
                /*
                List will remain as an empty list and loading will be set to false and error will be saved with the
                error message.
                 */
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
    /*
    To make the data or Model to be ready for the View we have made this RecipeState data class
    with these three variables.
     */
}