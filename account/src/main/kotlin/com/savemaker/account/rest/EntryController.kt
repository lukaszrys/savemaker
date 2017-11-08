package com.savemaker.account.rest

import com.savemaker.account.domain.Entry
import com.savemaker.account.service.EntryService
import com.savemaker.account.service.observer.EntryManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/entry")
class EntryController constructor (@Autowired entryService: EntryService){
    val entryManager = EntryManager()
    val entryService = entryService
    init {
        entryManager.addListener(entryService)
    }

    @PreAuthorize("#oauth2.hasScope('ui')")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun addEntry(@RequestBody entry: Entry, principal: Principal) {
        entryManager.notifyObservers(entry, principal.name)
    }

    @PreAuthorize("#oauth2.hasScope('ui')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun listEntries(principal: Principal) : List<Entry> {
        return entryService.listEntriesByEmail(principal.name)
    }

}