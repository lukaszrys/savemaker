package com.savemaker.account.service.observer

import com.savemaker.account.domain.Entry
import java.util.concurrent.ConcurrentLinkedQueue

class EntryManager : EntryObserver{
    private val listeners = ConcurrentLinkedQueue<EntryListener>()

    override fun addListener(entryListener: EntryListener) {
        listeners.add(entryListener)
    }

    fun notifyObservers(entry: Entry, email: String){
        listeners.forEach {listener -> listener.addEntry(entry, email)}
    }
}