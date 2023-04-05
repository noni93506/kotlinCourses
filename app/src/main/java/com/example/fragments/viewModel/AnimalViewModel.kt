package com.example.fragments.viewModel

import androidx.databinding.ObservableField
import com.example.fragments.model.Animal

class AnimalVM(val animal: Animal) {

    val info= ObservableField<String>("${animal.name} barked ${animal.shoutCount} times..")
fun shout(){
    animal.shoutCount ++
    info.set("animal ${animal.name} shouted ${animal.shoutCount} times")
}
}