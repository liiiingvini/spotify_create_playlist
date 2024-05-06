package org.example;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationCodeExample {
    private static final String clientId = "9c40f2c6da934356907984f4628a2f5d";
    private static final String clientSecret = "753f1dee67354213b0fe4e190a8062b8";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/callback");
    private static final String accessToken = "BQA3qrEuxtSwzOoPFmFOIcuN0dONAU4HXpxAl1r2vU2Bj1ohPwRjBvtiVsryyVEpjwQJzgH1efLtaTWIlxiueE371Hoj0lPQlNAD-D-xCAxODv4027cU6V0tmRJ1D8ZLr-iRQqMdKo6aG-JsXKxcdhZ4xx4Zr7Afs5J3PueyE-wJi2hPd-xlVeE6GMlglyOV9Gj2RKVtPnM98djqqeOFuqUyDhWUC3xdVhtTrQcvHcnTFPeMDnV4488rya41WRE42DFw8lsEFaDUthS9h9CA_WzBL5BwmW7lku0NNtQCBw";
    private static final String code = "AQBcV7he-qJW27H3o6wDaZcrUOg8gGPIzlAuVejXnTcl8Q-yuTe9a7YXs92jjgmQADhXZ-St7Y8C3zcQ78a1Wy7zQj7WvvutMRa0KcFEBUpmJ4o4HKAwONNay64MTJiP-zzOTU8h1MsP1LxpcWmqYQBcfP0QJ5p6OIID9tp85VvMClC3rRYqeXZ7nX9zvngPu7hoQPcSSIptov1YwhrhywFC983K5xotMM7wI8uwcIX2aJQnJBrTmErjBJqtlfrtLCIyOsEHXjUOncGZpF3wmKKJqDp4OeR2P3zCEnsGSzgLNE6ALKjbb2FFePetiYwi8lFrN1rrOxRAhPBoBtbSmaDYTA";
    static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();
    private static final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
            .build();

    public static void authorizationCode_Sync() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println(authorizationCodeCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        authorizationCode_Sync();
    }
}