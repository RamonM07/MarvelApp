package com.heroes.marvelapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroes.marvelapp.domain.model.getCharacters.CharactersState
import com.heroes.marvelapp.domain.usesCase.GetCharactersUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel
    @Inject
    constructor(
        private val getCharactersUsesCase: GetCharactersUsesCase,
    ) : ViewModel() {
        private val _charactersState = MutableStateFlow(CharactersState())
        val charactersState: StateFlow<CharactersState> = _charactersState.asStateFlow()

        init {
            getCharacters()
        }

        private fun getCharacters() {
            _charactersState.value = CharactersState(isLoading = true)
            viewModelScope.launch {
                getCharactersUsesCase
                    .execute()
                    .onSuccess { result ->
                        _charactersState.value = CharactersState(heroes = result)
                        result.forEach {
                            Log.d("Result:", it.name)
                        }
                        Log.d(
                            "RESULT:: ",
                            "${result.forEach {
                                it.name
                            }}",
                        )
                    }.onFailure {
                        _charactersState.value = CharactersState(error = it.message)
                        Log.d("RESULT:: ", "${it.message}")
                    }
            }
        }
    }
