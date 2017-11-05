package com.savemaker.account.service.observer

interface EntryObserver {
    fun addListener(entryListener: EntryListener)
}