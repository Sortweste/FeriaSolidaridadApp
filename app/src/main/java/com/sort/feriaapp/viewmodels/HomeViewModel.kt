package com.sort.feriaapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.dao.ArticleDAO

class HomeViewModel: ViewModel(){
    private lateinit var titleList: MutableList<String>
    private lateinit var institutionList: MutableList<String>
    var title = ""
    var institution = ""
    private fun resetTitleList() {
        titleList = mutableListOf(
            "Atención al cliente",
            "Nóminas",
            "Ventas y Mercadotécnia",
            "Gestión de activos",
            "Publicidad",
            "Relaciones públicas",
            "Gestión de la calidad",
            "Departamento legal",
            "Finanzas",
            "Recursos humanos",
            "Contabilidad",
            "Investigación y desarrollo",
            "Soporte técnico",
            "Relaciones con los medios",
            "Google",
            "Altavista",
            "Ibuprofen (Rx)",
            "Alendronate Sodium",
            "bag",
            "roll",
            "Lipitor"
        )
        titleList.shuffle()
    }
    private fun resetInstitutionList() {
        institutionList = mutableListOf(
            "Atención al cliente",
            "Nóminas",
            "Ventas y Mercadotécnia",
            "Gestión de activos",
            "Publicidad",
            "Relaciones públicas",
            "Gestión de la calidad",
            "Departamento legal",
            "Finanzas",
            "Recursos humanos",
            "Contabilidad",
            "Investigación y desarrollo",
            "Soporte técnico",
            "Relaciones con los medios",
            "Google",
            "Altavista",
            "Ibuprofen (Rx)",
            "Alendronate Sodium",
            "bag",
            "roll",
            "Lipitor"
        )
        titleList.shuffle()
    }
    init {
        resetTitleList()
        resetInstitutionList()
        nextPublication()
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
    fun onSkip() {
        nextPublication()
    }
    private fun nextPublication() {
        if (!titleList.isEmpty() && !institutionList.isEmpty()) {
            title = titleList.removeAt(0)
            institution = institutionList.removeAt(0)
        }
    }

}