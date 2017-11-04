package com.savemaker.account.service

import com.savemaker.account.domain.dto.AppUserDto

interface AccountService {
    fun register(appUserDto: AppUserDto)
}