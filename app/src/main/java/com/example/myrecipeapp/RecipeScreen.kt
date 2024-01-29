package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

/*
For our View in  MVVM we are setting up function RecipeScreen
 */
@Composable
fun RecipeScreen(modifier: Modifier = Modifier){
    val recipeViewModel: MainViewModel = viewModel()
    /*
    Creating an object of MainViewModel type
     */
    val viewstate by recipeViewModel.categoriesState
    /*
    We know that recipeViewModel is an object that have categoriesState to maintain state of our data.
    here we are renaming it by using by keyword to be called as viewstate
     */
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            /*
            If loading is to be  true a loading sign will appear and the above line is just another way
            modifying our Composable
             */

            viewstate.error != null ->{
                Text("ERROR OCCURED!!")
            }// Text mssg will appear if viewstate.error is not null
            else->{
                //Display Categories
                CategoryScreen(categories = viewstate.list)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>){
    /*
    To display the List we have made this particular function and we have used LazyVerticalGrid which will
    display two items in a single row and for particular item of the list on how to is be displayed we have
    used CategoryItem function which displays Image and name of that type of recipe.
     */
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categories){
            category ->
            CategoryItem(category = category)
        }
    }
}

//How each Items should looks like
@Composable
fun CategoryItem(category: Category){
    Column(modifier = Modifier.padding(8.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter =rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )// helps us loading the image.
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp)
        )// Helps writing recipe category name on the screen
    }
}