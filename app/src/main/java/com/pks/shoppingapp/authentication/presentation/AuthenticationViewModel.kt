package com.pks.shoppingapp.authentication.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.authentication.domain.model.UserData
import com.pks.shoppingapp.authentication.domain.usecase.CreateUserUseCase
import com.pks.shoppingapp.authentication.domain.usecase.GetUserUseCase
import com.pks.shoppingapp.authentication.domain.usecase.SignInUseCase
import com.pks.shoppingapp.authentication.domain.usecase.UpdateUserUseCase
import com.pks.shoppingapp.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingapp.authentication.presentation.profile.UpdateUserDataState
import com.pks.shoppingapp.authentication.presentation.signup.SignupScreenState
import com.pks.shoppingapp.common.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserDataUseCase: UpdateUserUseCase,
    private val signInUseCase:SignInUseCase

) : ViewModel() {
    private val _signupScreenState = MutableStateFlow(SignupScreenState())
    val signupScreenState = _signupScreenState.asStateFlow()
    private val _userDataUploadState = MutableStateFlow(UpdateUserDataState())
    val userDataUploadState = _userDataUploadState.asStateFlow()
    private val _loginScreenState = MutableStateFlow(SignupScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()
    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()
    val profileUi = mutableStateOf<ProfileScreenState>(ProfileScreenState())

    fun createUser(userData: UserData) {
        viewModelScope.launch {

            createUserUseCase.createUser(userData).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _signupScreenState.value = SignupScreenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _signupScreenState.value = SignupScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = userData
                        )
                        _signupScreenState.value = SignupScreenState(userData = it.data)
                    }
                }
            }
        }

    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {

            signInUseCase.singIn(email = email, password = password).collectLatest {
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
        Log.d("Data is loading befor", profileScreenState.value.toString())
        _profileScreenState.value = _profileScreenState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            Log.d("Data is loading", "data")
            delay(6000)
            getUserUseCase.getUserWithUid(uid).collectLatest {

                when (it) {
                    is ResultState.Error -> {
                        Log.d("Data is loading", "error")
                        _profileScreenState.value =
                            _profileScreenState.value.copy(
                                isLoading = false,
                                errorMessage = it.message.toString()
                            )
                    }

                    ResultState.Loading -> {
                        Log.d("Data is loading", "Loading")
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        //profileUi.value = ProfileScreenState(userData = it.data)

                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = it.data.userData
                        )
//                        Log.d("Data is loading in", profileScreenState.value.data.toString())
                    }
                }
                Log.d("Data is loading after", profileScreenState.value.toString())
            }

        }

    }

    fun updateUserdata(userData: UserData) {
        viewModelScope.launch {
            updateUserDataUseCase.updateUserData(userData).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _userDataUploadState.value =
                            UpdateUserDataState(error = it.message.toString())
                    }

                    ResultState.Loading -> {
                        _userDataUploadState.value = UpdateUserDataState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = userData
                        )
                        _userDataUploadState.value = UpdateUserDataState(isLoaded = true)
                    }
                }
            }
        }
    }

    fun reAssignSignUp(){
        _signupScreenState.value = SignupScreenState()
    }

}