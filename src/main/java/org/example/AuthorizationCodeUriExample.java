package org.example;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.enums.AuthorizationScope;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationCodeUriExample {
    private static final String clientId = "9c40f2c6da934356907984f4628a2f5d";
    private static final String clientSecret = "753f1dee67354213b0fe4e190a8062b8";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/callback");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();
    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
            .scope(AuthorizationScope.USER_LIBRARY_MODIFY, AuthorizationScope.USER_LIBRARY_READ,AuthorizationScope.PLAYLIST_READ_PRIVATE, AuthorizationScope.PLAYLIST_MODIFY_PRIVATE, AuthorizationScope.PLAYLIST_MODIFY_PUBLIC,AuthorizationScope.USER_READ_PRIVATE)
            .build();
    public static void authorizationCodeUri_Sync() {
        final URI uri = authorizationCodeUriRequest.execute();

        System.out.println("URI: " + uri.toString());
    }

    public static void authorizationCodeUri_Async() {
        try {
            final CompletableFuture<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final URI uri = uriFuture.join();

            System.out.println("URI: " + uri.toString());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) {
        authorizationCodeUri_Sync();
        authorizationCodeUri_Async();
    }
}