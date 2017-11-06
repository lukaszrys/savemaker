package com.savemaker.account.oauth2

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.test.context.support.WithSecurityContextFactory
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import org.springframework.security.core.context.SecurityContextHolder



class MockOAuth2ScopeSecurityContextFactory : WithSecurityContextFactory<MockOAuth2Scope> {
    override fun createSecurityContext(mockOAuth2Scope: MockOAuth2Scope): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()
        val scope = mutableSetOf(mockOAuth2Scope.scope)
        val request = OAuth2Request(null, "test", null, true, scope, null, null, null, null)
        val auth = OAuth2Authentication(request, null)
        context.authentication = auth
        return context
    }
}