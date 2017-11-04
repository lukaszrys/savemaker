package com.savemaker.authorization.security

import com.savemaker.authorization.domain.AppUser
import com.savemaker.authorization.domain.repository.AppUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class OAuthUserDetailsService : UserDetailsService{

    @Autowired
    lateinit var appUserRepository : AppUserRepository;

    override fun loadUserByUsername(username: String): UserDetails {
        val user : Optional<AppUser> = appUserRepository.findByUsername(username)

        if (!user.isPresent){
            throw UsernameNotFoundException(username)
        }

        return user.get()
    }

}