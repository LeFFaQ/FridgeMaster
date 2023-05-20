package ru.lffq.fmaster.feature_profile.ui.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import ru.lffq.fmaster.feature_profile.domain.User
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    val user = flow<User> { User.Anonymous }


}