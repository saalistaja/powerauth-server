/*
 * PowerAuth Server and related software components
 * Copyright (C) 2018 Wultra s.r.o.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.getlime.security.powerauth.app.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class holding the configuration data of this PowerAuth Server
 * instance. Default values are in "application.properties" file.
 *
 * @author Petr Dvorak, petr@wultra.com
 */
@Configuration
@ConfigurationProperties("ext")
public class PowerAuthServiceConfiguration {

    /**
     * When asking for server status, this variable will be returned as application
     * name.
     */
    @Value("${powerauth.service.applicationName}")
    private String applicationName;

    /**
     * When asking for server status, this variable will be returned as application
     * display name.
     */
    @Value("${powerauth.service.applicationDisplayName}")
    private String applicationDisplayName;

    /**
     * When asking for server status, this variable will be returned as application
     * system environment (for example, 'dev' or 'prod').
     */
    @Value("${powerauth.service.applicationEnvironment}")
    private String applicationEnvironment;

    /**
     * If this variable is set to true, server will check credentials client uses
     * to access the service and compare them with credentials stored in 'pa_integration'
     * table.
     */
    @Value("${powerauth.service.restrictAccess}")
    private Boolean restrictAccess;

    /**
     * When a duplicate activation ID is encountered during the activation, how
     * many times generate a new one.
     */
    @Value("${powerauth.service.crypto.generateActivationIdIterations}")
    private int activationGenerateActivationIdIterations;

    /**
     * When a duplicate token ID is encountered during the token generation, how
     * many times generate a new one.
     */
    @Value("${powerauth.service.crypto.generateTokenIdIterations}")
    private int generateTokenIdIterations;

    /**
     * When a duplicate activation code is encountered during the
     * activation, how many times generate a new one.
     */
    @Value("${powerauth.service.crypto.generateActivationCodeIterations}")
    private int activationGenerateActivationCodeIterations;

    /**
     * When a duplicate recovery code is encountered, how many times generate a new one.
     */
    @Value("${powerauth.service.crypto.generateRecoveryCodeIterations}")
    private int generateRecoveryCodeIterations;

    /**
     * How many milliseconds should be CREATED or OTP_USED record usable for
     * completing the activation.
     */
    @Value("${powerauth.service.crypto.activationValidityInMilliseconds}")
    private int activationValidityBeforeActive;

    /**
     * How many failed signatures cause activation record blocking.
     */
    @Value("${powerauth.service.crypto.signatureMaxFailedAttempts}")
    private long signatureMaxFailedAttempts;

    /**
     * When validating the signature, how many iterations ahead too look in case
     * signature fails for the first counter value.
     */
    @Value("${powerauth.service.crypto.signatureValidationLookahead}")
    private long signatureValidationLookahead;

    /**
     * Whether HTTP proxy is enabled for outgoing HTTP requests.
     */
    @Value("${powerauth.service.http.proxy.enabled}")
    private Boolean httpProxyEnabled;

    /**
     * HTTP proxy host.
     */
    @Value("${powerauth.service.http.proxy.host}")
    private String httpProxyHost;

    /**
     * HTTP proxy port.
     */
    @Value("${powerauth.service.http.proxy.port}")
    private Integer httpProxyPort;

    /**
     * HTTP proxy username, use only in case HTTP proxy authentication is required.
     */
    @Value("${powerauth.service.http.proxy.username}")
    private String httpProxyUsername;

    /**
     * HTTP proxy password, use only in case HTTP proxy authentication is required.
     */
    @Value("${powerauth.service.http.proxy.password}")
    private String httpProxyPassword;

    /**
     * HTTP connection timeout.
     */
    @Value("${powerauth.service.http.connection.timeout}")
    private Integer httpConnectionTimeout;

    /**
     * Token timestamp validity in milliseconds, checked before validating the token.
     */
    @Value("${powerauth.service.token.timestamp.validity}")
    private long tokenTimestampValidityInMilliseconds;

    /**
     * Master DB encryption key.
     */
    @Value("${powerauth.server.db.master.encryption.key}")
    private String masterDbEncryptionKey;

    /**
     * How many failed usages of recovery code block the recovery code.
     */
    @Value("${powerauth.service.recovery.maxFailedAttempts}")
    private long recoveryMaxFailedAttempts;

    /**
     * Get application name, usually used as a "unique code" for the application within
     * a server infrastructure.
     *
     * @return Application name.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Set application name.
     *
     * @param applicationName Application name.
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * Get application display name, usually used as a "visual representation" of the
     * application within a server infrastructure.
     *
     * @return Application display name.
     */
    public String getApplicationDisplayName() {
        return applicationDisplayName;
    }

    /**
     * Set application display name.
     *
     * @param applicationDisplayName Application display name.
     */
    public void setApplicationDisplayName(String applicationDisplayName) {
        this.applicationDisplayName = applicationDisplayName;
    }

    /**
     * Get the application environment name.
     *
     * @return Application environment name.
     */
    public String getApplicationEnvironment() {
        return applicationEnvironment;
    }

    /**
     * Set the application environment name.
     *
     * @param applicationEnvironment Application environment name.
     */
    public void setApplicationEnvironment(String applicationEnvironment) {
        this.applicationEnvironment = applicationEnvironment;
    }

    /**
     * Get the value of a flag that indicates that access to the PA2.0 Server should be restricted
     * to predefined integrations.
     *
     * @return Flag with access restriction information.
     */
    public Boolean getRestrictAccess() {
        return restrictAccess;
    }

    /**
     * Set the value of a flag that indicates that access to the PA2.0 Server should be restricted
     * to predefined integrations.
     *
     * @param restrictAccess Flag with access restriction information.
     */
    public void setRestrictAccess(Boolean restrictAccess) {
        this.restrictAccess = restrictAccess;
    }

    /**
     * Get number of activation ID generation attempts in case of collision.
     * @return Retry iteration count (10, by default).
     */
    public int getActivationGenerateActivationIdIterations() {
        return activationGenerateActivationIdIterations;
    }

    /**
     * Set number of activation ID generation attempts in case of collision.
     * @param activationGenerateActivationIdIterations Retry iteration count (10, by default).
     */
    public void setActivationGenerateActivationIdIterations(int activationGenerateActivationIdIterations) {
        this.activationGenerateActivationIdIterations = activationGenerateActivationIdIterations;
    }

    /**
     * Get number of token ID generation attempts in case of collision.
     * @return Retry iteration count (10, by default).
     */
    public int getGenerateTokenIdIterations() {
        return generateTokenIdIterations;
    }

    /**
     * Set number of token ID generation attempts in case of collision.
     * @param generateTokenIdIterations Retry iteration count (10, by default).
     */
    public void setGenerateTokenIdIterations(int generateTokenIdIterations) {
        this.generateTokenIdIterations = generateTokenIdIterations;
    }

    /**
     * Get number of activation code generation attempts in case of collision.
     * @return Retry iteration count (10, by default).
     */
    public int getActivationGenerateActivationCodeIterations() {
        return activationGenerateActivationCodeIterations;
    }

    /**
     * Set number of activation code generation attempts in case of collision.
     * @param activationGenerateActivationCodeIterations Retry iteration count (10, by default).
     */
    public void setActivationGenerateActivationCodeIterations(int activationGenerateActivationCodeIterations) {
        this.activationGenerateActivationCodeIterations = activationGenerateActivationCodeIterations;
    }

    /**
     * Get number of recovery code generation attempts in case of collision.
     * @return Retry iteration count (10, by default).
     */
    public int getGenerateRecoveryCodeIterations() {
        return generateRecoveryCodeIterations;
    }

    /**
     * Set number of recovery code generation attempts in case of collision.
     * @param generateRecoveryCodeIterations Retry iteration count (10, by default).
     */
    public void setGenerateRecoveryCodeIterations(int generateRecoveryCodeIterations) {
        this.generateRecoveryCodeIterations = generateRecoveryCodeIterations;
    }

    /**
     * Get default number of maximum failed attempts.
     * @return Maximum failed attempts (5, by default).
     */
    public long getSignatureMaxFailedAttempts() {
        return signatureMaxFailedAttempts;
    }

    /**
     * Set default number of maximum failed attempts.
     * @param signatureMaxFailedAttempts Maximum failed attempts (5, by default).
     */
    public void setSignatureMaxFailedAttempts(long signatureMaxFailedAttempts) {
        this.signatureMaxFailedAttempts = signatureMaxFailedAttempts;
    }

    /**
     * Get length of the period of activation record validity during activation.
     * @return How long the activation is valid before it expires (2 minutes, in milliseconds, by default).
     */
    public int getActivationValidityBeforeActive() {
        return activationValidityBeforeActive;
    }

    /**
     * Get length of the period of activation record validity during activation.
     * @param activationValidityBeforeActive How long the activation is valid before it expires (2 minutes, in milliseconds by defaults).
     */
    public void setActivationValidityBeforeActive(int activationValidityBeforeActive) {
        this.activationValidityBeforeActive = activationValidityBeforeActive;
    }

    /**
     * Get the signature validation lookahead.
     * @return Signature validation lookahead.
     */
    public long getSignatureValidationLookahead() {
        return signatureValidationLookahead;
    }

    /**
     * Set the signature validation lookahead.
     * @param signatureValidationLookahead Signature validation lookahead.
     */
    public void setSignatureValidationLookahead(long signatureValidationLookahead) {
        this.signatureValidationLookahead = signatureValidationLookahead;
    }

    /**
     * Get whether HTTP proxy is enabled.
     * @return Whether HTTP proxy is enabled.
     */
    public Boolean getHttpProxyEnabled() {
        return httpProxyEnabled;
    }

    /**
     * Set whether HTTP proxy is enabled.
     * @param httpProxyEnabled Whether HTTP proxy is enabled.
     */
    public void setHttpProxyEnabled(Boolean httpProxyEnabled) {
        this.httpProxyEnabled = httpProxyEnabled;
    }

    /**
     * Get HTTP proxy host.
     * @return HTTP proxy host.
     */
    public String getHttpProxyHost() {
        return httpProxyHost;
    }

    /**
     * Set HTTP proxy host.
     * @param httpProxyHost HTTP proxy host.
     */
    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    /**
     * Get HTTP proxy port.
     * @return HTTP proxy port.
     */
    public Integer getHttpProxyPort() {
        return httpProxyPort;
    }

    /**
     * Set HTTP proxy port.
     * @param httpProxyPort HTTP proxy port.
     */
    public void setHttpProxyPort(Integer httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }

    /**
     * Get HTTP proxy username.
     * @return HTTP proxy username.
     */
    public String getHttpProxyUsername() {
        return httpProxyUsername;
    }

    /**
     * Set HTTP proxy username.
     * @param httpProxyUsername HTTP proxy username.
     */
    public void setHttpProxyUsername(String httpProxyUsername) {
        this.httpProxyUsername = httpProxyUsername;
    }

    /**
     * Get HTTP proxy password.
     * @return HTTP proxy password.
     */
    public String getHttpProxyPassword() {
        return httpProxyPassword;
    }

    /**
     * Set HTTP proxy password.
     * @param httpProxyPassword HTTP proxy password.
     */
    public void setHttpProxyPassword(String httpProxyPassword) {
        this.httpProxyPassword = httpProxyPassword;
    }

    /**
     * Get HTTP connection timeout.
     * @return HTTP connection timeout.
     */
    public Integer getHttpConnectionTimeout() {
        return httpConnectionTimeout;
    }

    /**
     * Set HTTP connection timeout.
     * @param httpConnectionTimeout HTTP connection timeout.
     */
    public void setHttpConnectionTimeout(Integer httpConnectionTimeout) {
        this.httpConnectionTimeout = httpConnectionTimeout;
    }

    /**
     * Get the token timestamp validity in milliseconds.
     * @return Token timestamp validity in milliseconds.
     */
    public long getTokenTimestampValidityInMilliseconds() {
        return tokenTimestampValidityInMilliseconds;
    }

    /**
     * Set the token timestamp validity in milliseconds.
     * @param tokenTimestampValidityInMilliseconds Token timestamp validity in milliseconds.
     */
    public void setTokenTimestampValidityInMilliseconds(long tokenTimestampValidityInMilliseconds) {
        this.tokenTimestampValidityInMilliseconds = tokenTimestampValidityInMilliseconds;
    }

    /**
     * Get master DB encryption key.
     * @return Master DB encryption key.
     */
    public String getMasterDbEncryptionKey() {
        return masterDbEncryptionKey;
    }

    /**
     * Set master DB encryption key.
     * @param masterDbEncryptionKey Master DB encryption key.
     */
    public void setMasterDbEncryptionKey(String masterDbEncryptionKey) {
        this.masterDbEncryptionKey = masterDbEncryptionKey;
    }

    /**
     * Get default number of maximum failed attempts for recovery codes.
     * @return Maximum failed attempts for recovery codes (5, by default).
     */
    public long getRecoveryMaxFailedAttempts() {
        return recoveryMaxFailedAttempts;
    }

    /**
     * Set default number of maximum failed attempts for recovery codes.
     * @param recoveryMaxFailedAttempts Maximum failed attempts for recovery codes (5, by default).
     */
    public void setRecoveryMaxFailedAttempts(long recoveryMaxFailedAttempts) {
        this.recoveryMaxFailedAttempts = recoveryMaxFailedAttempts;
    }
}
