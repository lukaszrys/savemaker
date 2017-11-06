package com.savemaker.account.oauth2

import org.springframework.security.test.context.support.WithSecurityContext

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = MockOAuth2ScopeSecurityContextFactory::class)
annotation class MockOAuth2Scope (val scope: String)