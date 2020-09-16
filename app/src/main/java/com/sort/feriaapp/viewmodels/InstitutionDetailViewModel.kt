package com.sort.feriaapp.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.sort.feriaapp.data.InstitutionWithEvents
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.data.repositories.InstitutionRepository
import kotlinx.coroutines.launch

/*ViewModel for InstitutionDetailFragment*/
class InstitutionDetailViewModel @ViewModelInject constructor(private val institutionRepository: InstitutionRepository, @Assisted savedStateHandle: SavedStateHandle): ViewModel() {

    /*Reading NavArgs properties*/
    private val savedStateHandleViewModel = savedStateHandle
    private val institutionId: String = savedStateHandleViewModel.get("institutionId")!!

    /*Get Institutions from database*/
    private val _getInstitutionInfo: LiveData<InstitutionWithEvents> = institutionRepository.getInstitutionInfoById(institutionId).asLiveData()

    val institutionInfo: LiveData<InstitutionWithEvents>
    get() = _getInstitutionInfo

    /*Initial state for Events RecyclerView inside InstitutionDetailFragment*/
    val events: List<EventMinimal> = emptyList()

    /*Unimplemented: Can be deleted!*/
    //fun saveWebViewState() = savedStateHandleViewModel.set("WebViewState")
    //fun getWebViewState() = savedStateHandleViewModel.get("WebViewState")

}