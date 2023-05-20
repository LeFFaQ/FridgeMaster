package ru.lffq.fmaster.feature_profile.ui.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.placeholder
import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_profile.domain.User
import ru.lffq.fmaster.ui.theme.rememberPlaceholder

@Composable
fun ProfileCard(user: User) {
    val context = LocalContext.current

    var imageLoading by remember {
        mutableStateOf(
            true
        )
    }
    val painter = if (user is User.Authorized) {
        // эта хрень не работает с webp
        rememberAsyncImagePainter(model = user.profilePic, onSuccess = {
            imageLoading = false
        })
    } else {
        imageLoading = false
        painterResource(id = R.drawable.profile_placeholder)
    }



    Row(
        Modifier
            .fillMaxWidth()
            .height(124.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter, contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .placeholder(
                    visible = imageLoading,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(0.7f),
                    highlight = rememberPlaceholder(),
                    shape = RoundedCornerShape(16.dp)
                ),
        )
        Column(
            verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp)
        ) {
            Text(
                text = user.nickname,
                style = MaterialTheme.typography.titleLarge.copy(color = Color(0xff5f5f5f))
            )
            if (user is User.Authorized) {
                Text(text = "${user.firstName} ${user.secondName}", color = Color(0xff5f5f5f))
            } else {
                //imageLoading = false

                TextButton(onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.not_working),
                        Toast.LENGTH_SHORT
                    ).show()
                }, contentPadding = PaddingValues(0.dp)) {
                    Text(text = "Зарегистрироваться")
                }
            }
        }
    }
}

@Preview
@Composable
fun AnonymousProfilePreview() {
    ProfileCard(user = User.Anonymous)
}

@Preview
@Composable
fun AuthorizedProfilePreview() {
    ProfileCard(
        user = User.Authorized(
            nickname = "LeFFaQ",
            firstName = "Данил",
            secondName = "Романов",
            profilePic = "https://sun9-8.userapi.com/impg/syJ4RQrmKOOppoiKYb9bFh-l5LesA0oMBlSyBA/l59oG0_pdgk.jpg?size=2560x2560&quality=95&sign=6a6b40d84998b34e7e60351935fe206b&type=album"
        )
    )
}