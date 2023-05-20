package ru.lffq.fmaster.ui.base.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.window.layout.DisplayFeature
import kotlinx.coroutines.channels.consumeEach
import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_detail.ui.Detail
import ru.lffq.fmaster.feature_detail.ui.DetailType
import ru.lffq.fmaster.feature_detail.ui.DetailViewModel
import ru.lffq.fmaster.feature_feed.ui.feed.Feed
import ru.lffq.fmaster.feature_feed.ui.feed.FeedViewModel
import ru.lffq.fmaster.feature_inventory.ui.Inventory
import ru.lffq.fmaster.feature_inventory.ui.InventoryViewModel
import ru.lffq.fmaster.feature_profile.ui.profile.Profile
import ru.lffq.fmaster.feature_profile.ui.profile.ProfileViewModel
import ru.lffq.fmaster.ui.components.WidthClass


sealed class MainSubScreen(
    @StringRes val title: Int, @DrawableRes val icon: Int? = null, val destination: String

) {
    //хз насколько правильно запихивать прямо @StringRes сюда
    object Feed :
        MainSubScreen(R.string.feed_title, destination = "feed", icon = R.drawable.ic_feed_24)

    object Inventory : MainSubScreen(
        R.string.inventory_title, destination = "inventory", icon = R.drawable.ic_inventory_24
    )

    object AddToInventory : MainSubScreen(R.string.addinv_title, destination = "add")

    object Profile : MainSubScreen(
        R.string.profile_title, destination = "profile", icon = R.drawable.ic_person_24
    )

    object Detail : MainSubScreen(R.string.detail_title, destination = "detail/{type}/{id}")

    object Search : MainSubScreen(R.string.search_title, destination = "search")

    object Catalog : MainSubScreen(R.string.catalog_title, destination = "catalog")

    companion object {
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNavigation(
    navController: NavHostController,
    widthClass: WidthClass,
    pagerState: PagerState,
    snackbarState: SnackbarHostState,
    displayFeatures: List<DisplayFeature>
) {

    NavHost(navController, startDestination = MainSubScreen.Inventory.destination) {
        composable(route = MainSubScreen.Feed.destination) {
            val vm = hiltViewModel<FeedViewModel>()

            LaunchedEffect(snackbarState) {
                vm.errorChannel.consumeEach {
                    snackbarState.showSnackbar(it, duration = SnackbarDuration.Short)
                }
            }

            Feed(
                navController = navController,
                vm = vm,
                widthClass = widthClass,
                displayFeatures = displayFeatures
            )
        }
        composable(route = MainSubScreen.Inventory.destination) {

            val vm = hiltViewModel<InventoryViewModel>()
            Inventory(vm, pagerState, widthClass)
        }
        composable(route = MainSubScreen.Profile.destination) {
            val vm = hiltViewModel<ProfileViewModel>()
            Profile(vm)
            // Add VM
        }
        composable(
            MainSubScreen.Detail.destination,
            arguments = listOf(
                navArgument("type", builder = { type = NavType.StringType }),
                navArgument("id", builder = { type = NavType.IntType }),
            )
        ) {
            val vm = hiltViewModel<DetailViewModel>(it)
            val recipe by vm.information.collectAsState()
            recipe?.let {
                Detail(type = DetailType.Recipe, recipe!!)
            }
            val details by vm.article.collectAsState()
            details?.let { _details ->
                Detail(_details)
            }

        }
        composable(MainSubScreen.AddToInventory.destination) {

        }

        composable(MainSubScreen.Search.destination) {

        }
        composable(MainSubScreen.Catalog.destination) {

        }
    }
}