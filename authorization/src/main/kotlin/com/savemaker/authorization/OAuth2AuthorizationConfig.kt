package com.savemaker.authorization

import com.savemaker.authorization.security.OAuthUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore

@Configuration
@EnableAuthorizationServer
class OAuth2AuthorizationConfig : AuthorizationServerConfigurerAdapter() {

    //var tokenStore : TokenStore = InMemoryTokenStore()

    @Autowired
    lateinit var authenticationManager : AuthenticationManager

    @Autowired
    lateinit var oAuthUserDetailsService : OAuthUserDetailsService

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient("web")
                .authorizedGrantTypes("refresh_token", "password")
                .secret("web-secret")
                .scopes("ui")
                    .and()
                .withClient("mail")
                .secret("mail")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service")
                    .and()
                .withClient("account")
                .secret("account")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service")
                    .and()
                .withClient("account-statistics")
                .secret("account-statistics")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service")

    }

    fun tokenStore() : TokenStore {
        return InMemoryTokenStore()
    }
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(oAuthUserDetailsService)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
    }
}