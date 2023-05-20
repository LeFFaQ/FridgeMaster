package ru.lffq.fmaster.feature_profile.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.lffq.fmaster.feature_profile.domain.User


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(vm: ProfileViewModel) {
    val user = vm.user.collectAsState(User.Anonymous)


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Профиль") })
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ProfileCard(
//                user = User.Authorized(
//                    nickname = "LeFFaQ",
//                    firstName = "Данил",
//                    secondName = "Романов",
//                    profilePic = "https://sun9-8.userapi.com/impg/syJ4RQrmKOOppoiKYb9bFh-l5LesA0oMBlSyBA/l59oG0_pdgk.jpg?size=2560x2560&quality=95&sign=6a6b40d84998b34e7e60351935fe206b&type=album"
//                )
                user = User.Anonymous
            )
        }
    }
}