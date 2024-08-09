package com.example.gmap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gmap.viewmodel.model.BottomNavigationItem
import com.example.gmap.viewmodel.usecase.GetNavigationItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel(){
    private val _isNetworkAvailable = MutableStateFlow(true)
    val isNetworkAvailable: StateFlow<Boolean> = _isNetworkAvailable

    init{
        checkNetworkStatus()
    }

    private fun checkNetworkStatus(){
        viewModelScope.launch {
            //Network Check
            _isNetworkAvailable.value= true
        }
    }
}
