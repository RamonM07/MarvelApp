package com.heroes.marvelapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroes.marvelapp.domain.usesCase.GetCharactersUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUsesCase: GetCharactersUsesCase
) : ViewModel() {


    init {
        getCharacters()
    }
    private fun getCharacters() {
        viewModelScope.launch {
            getCharactersUsesCase.execute()
                .onSuccess { result ->
                    result.forEach {
                        Log.d("Result:", it.name)
                    }
                    Log.d("RESULT:: ", "${result.forEach { 
                        it.name
                    }}")
                }
                .onFailure {
                    Log.d("RESULT:: ", "${it.message}")
                }
        }

    }
}


