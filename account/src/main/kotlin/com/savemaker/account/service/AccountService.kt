package com.savemaker.account.service

import com.savemaker.account.dto.AppUserDto

interface AccountService {
    fun register(appUserDto: AppUserDto)
}