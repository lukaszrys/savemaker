package com.savemaker.account.domain.repository

import com.savemaker.account.domain.Entry
import org.bson.types.ObjectId
import org.springframework.data.repository.PagingAndSortingRepository

interface EntryRepository : PagingAndSortingRepository<Entry, ObjectId>{
}