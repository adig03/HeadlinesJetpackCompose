package com.example.headlinejetpackcompose.presentation.onboardingScreen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.headlinejetpackcompose.models.Page
import com.example.headlinejetpackcompose.models.pages
import com.example.headlinejetpackcompose.presentation.onboardingScreen.OnBoardingViewModel
import com.example.headlinejetpackcompose.presentation.onboardingScreen.OnboardingEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {




    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        // ------------------ PAGER ------------------
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            ImageTitleDescSection(
                page = pages[page]
            )
        }



        // ------------------ INDICATOR + BUTTONS ------------------
        IndicatorNextButton(
            pageState = pagerState.currentPage,
            onNextClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    viewModel.onEvent(
                        OnboardingEvent.SaveAppEntry
                        
                    )
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            onBackClick = {
                if (pagerState.currentPage > 0) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            }
        )
    }
}

@Composable
fun ImageTitleDescSection(page: Page) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))
       Column(Modifier.padding(horizontal = 16.dp)){
           Text(text = page.title, fontSize = 24.sp)
           Text(text = page.description, fontSize = 18.sp)
       }

    }
}

@Composable
fun IndicatorNextButton(
    pageState: Int,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(pages.size) { index ->
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(if (index == pageState) Color.Black else Color.LightGray)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            if (pageState > 0) {
                Text(
                    text = "Back",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { onBackClick() }
                )
            }

            Button(onClick = onNextClick) {
                Text(if (pageState == pages.lastIndex) "Get Started" else "Next")
            }
        }
    }
}
