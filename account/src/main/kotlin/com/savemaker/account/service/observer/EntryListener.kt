package com.savemaker.account.service.observer

import com.savemaker.account.domain.Entry

interface EntryListener {
    fun addEntry(entry : Entry, email: String)
}