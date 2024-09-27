package com.pks.shoppingapp.ui_layer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.authentication.domain.model.UserData
import com.pks.shoppingapp.authentication.domain.usecase.CreateUserUseCase
import com.pks.shoppingapp.authentication.domain.usecase.GetUserUseCase
import com.pks.shoppingapp.authentication.domain.usecase.SignInUseCase
import com.pks.shoppingapp.authentication.domain.usecase.UpdateUserUseCase
import com.pks.shoppingapp.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingapp.authentication.presentation.signup.SignupScreenState
import com.pks.shoppingapp.common.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShoppingAppViewModel @Inject constructor(
    private val creaseUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    val auth: FirebaseAuth,
    val signInUseCase: SignInUseCase,
    val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val _signupScreenState = MutableStateFlow(SignupScreenState())
    val signupScreenState = _signupScreenState.asStateFlow()
    private val _loginScreenState = MutableStateFlow(SignupScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()
    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()

    init {
        // getUserByUid(uid = auth.currentUser!!.uid)
    }

    fun createUser(userData: UserData) {
        viewModelScope.launch {

            creaseUserUseCase.createUser(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _signupScreenState.value = SignupScreenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _signupScreenState.value = SignupScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _signupScreenState.value = SignupScreenState(userData = it.data)
                    }
                }
            }
        }

    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {

            signInUseCase.singIn(email = email, password = password).collect {
                when (it) {
                    is ResultState.Error -> {
                        _loginScreenState.value = SignupScreenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _loginScreenState.value = SignupScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _loginScreenState.value = SignupScreenState(userData = it.data)
                    }
                }
            }
        }

    }

    fun getUserByUid(uid: String) {
        viewModelScope.launch {
            getUserUseCase.getUserWithUid(uid).collectLatest {

                when (it) {
                    is ResultState.Error -> {
                        _profileScreenState.value =
                            ProfileScreenState(errorMessage = it.message.toString())
                    }

                    ResultState.Loading -> {
                        _profileScreenState.value = ProfileScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = ProfileScreenState(userData = it.data.userData)
                    }
                }
            }
        }
    }
}
