package com.example.gmap.viewmodel.usecase

import com.example.gmap.model.repository.NavigationRepository
import com.example.gmap.viewmodel.model.BottomNavigationItem

class GetNavigationItemsUseCase(private val repository: NavigationRepository) {
    fun execute(): List<BottomNavigationItem> {
        return repository.getNavigationItems()
    }
}