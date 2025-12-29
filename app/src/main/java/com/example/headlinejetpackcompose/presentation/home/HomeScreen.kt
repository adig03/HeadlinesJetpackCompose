package com.example.headlinejetpackcompose.presentation.home

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.headlinejetpackcompose.Navigation.Routes
import com.example.headlinejetpackcompose.R
import com.example.headlinejetpackcompose.presentation.Dimens.DefaultPadding
import com.example.headlinejetpackcompose.presentation.Dimens.MediumPadding1
import com.example.headlinejetpackcompose.presentation.common.ArticleList
import com.example.headlinejetpackcompose.presentation.common.SelfMadeSearchBar
import okhttp3.Route


@Composable
fun HomeScreen(navController: NavHostController) {

    val viewModel  = hiltViewModel<HomeViewModel>()
    val articles = viewModel.news.collectAsLazyPagingItems()


    // 'titles' will automatically update when paging data changes
    val titles by remember {

        // derivedStateOf recalculates ONLY when its dependencies change
        // (here: articles.itemCount / snapshot list)
        derivedStateOf {

            // Check if we have more than 10 loaded articles
            if (articles.itemCount > 10) {

                // Get currently loaded items from Paging
                articles.itemSnapshotList.items

                    // Take only the first 10 articles (index 0 to 9)
                    .slice(IntRange(start = 0, endInclusive = 9))

                    // Join all titles into a single string
                    // Each title is separated by a red square emoji 🟥
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }

            } else {
                // If fewer than 10 articles are loaded,
                // return an empty string to avoid errors
                ""
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.newapplogo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        SelfMadeSearchBar(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navController.navigate(Routes.SearchScreen)
            }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = DefaultPadding),
            articles = articles,
            onClick = {

            }
        )
    }





}










//@Preview(showBackground = true)
//@Composable
//fun ArticleCardShimmerPreview(){
//    HeadlineJetpackComposeTheme() {
//        ArticleCardShimmer(Modifier)
//    }
//
//}


//@Preview(showBackground = true)
//@Composable
//fun ArticleCardPreview(){
//    HeadlineJetpackComposeTheme() {
//    }
//
//}
