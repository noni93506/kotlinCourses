package com.example.fragments.viewModel

import com.example.fragments.model.Animal

class AnimalViewModel(val animal: Animal) {

     var info =("animal ${animal.name} shouted ${animal.shoutCount} times" )
fun shout(){
    animal.shoutCount ++
    info = ("animal ${animal.name} shouted ${animal.shoutCount} times")
}
}