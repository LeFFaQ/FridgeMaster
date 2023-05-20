package ru.lffq.fmaster.ui.components

sealed interface WidthClass {

    object Compact : WidthClass
    object Medium : WidthClass
    object Expanded : WidthClass
}