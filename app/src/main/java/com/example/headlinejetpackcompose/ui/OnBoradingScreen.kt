package com.example.headlinejetpackcompose.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.headlinejetpackcompose.Navigation.Routes
import com.example.headlinejetpackcompose.R
import com.example.headlinejetpackcompose.models.Page
import com.example.headlinejetpackcompose.models.pages



@Composable
fun OnboardingScreen(navController: NavController){




    var pageState by remember{
        mutableStateOf(0)
    }




        Column(Modifier.fillMaxSize()){
            ImageTitleDescSection(Modifier.fillMaxHeight(0.8f)
                , pages[pageState])
            Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
            IndicatorNextButton(pageState = pageState , onClick = {
                if(pageState == pages.size -1){
                    navController.navigate(Routes.HomeScreen){
                        popUpTo(Routes.OnboardingScreen){
                            inclusive = true
                        }
                    }
                }else{
                    pageState = (pageState + 1)
                }

            } ,
                onBackPress = {
                    if(pageState > 0)
                    pageState = (pageState - 1)


                })
                }
        }





@Composable
fun ImageTitleDescSection(
    modifier: Modifier,
    page: Page
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = page.image),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            contentScale = ContentScale.Crop
        )



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = page.title,
                fontSize = 24.sp
            )
            Text(
                text = page.description,
                fontSize = 20.sp
            )
        }
    }
}


@Composable
fun IndicatorNextButton(pageState: Int, onClick: () -> Unit , onBackPress:() -> Unit ){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){


       Row(Modifier.padding(8.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.spacedBy(8.dp)){

           repeat(pages.size){
               Box(modifier = Modifier
                   .size(12.dp)
                   .clip(CircleShape)
                   .background(
                       if(pageState == it) Color.Black
                   else Color.Gray
                   )
                   )
           }
       }

        Row(modifier = Modifier.padding(8.dp) , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.spacedBy(12.dp)){
            Text(
                if(pageState == 0) "" else "Back",
                fontSize = 16.sp,
                color = Color.Gray ,
                modifier = Modifier.clickable{
                onBackPress()
            }
            )
            Button(onClick = onClick) {
                Text(text = if(pageState == pages.size - 1) "Get Started" else "Next" , fontSize = 16.sp , color = Color.White)
            }
        }

       }



}







