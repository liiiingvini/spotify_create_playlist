package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.*;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Deleted_dublicat {
    private static final String accessToken = "BQBTNTlrG1-BturDIMDihug-vkUqXhrQZPrlN0pKhWIa3qSfE3BcwxeP0WPjd_kfH1dpY5e8eLUzOq-TdqAF3DMog3ntxLobG7nexpazIw2_3_3rL2rybum-H0DANSg-ouDHcw5NJqxZbiz-CUi9aAqJj_AmxY-EkNmnxJlmCdtgFzPACFd4rhqCqWXxIYHS5FAhqDvsDgJR-bh-5XqcRPQkj88pr7fQ284_SwewWD6jGbG2ehyKMMMy17yU399LqfZyWT7xnKD4ZPBbie8XaSFUoKzSnCoNVQsgPbuQ5w";
    private static final String playlistId = "2tmL6PiNKlR3H7z7epYDWb";
    private static final String userId = "a3da87tfi4nm46go75wmaw0xq";
    private static String name;
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi
            .getPlaylistsItems(playlistId)
            .build();
    public static String[] getPlaylistsItems_Sync() {
        try {

            final Paging<PlaylistTrack> tracks = getPlaylistsItemsRequest.execute();
            ArrayList<String> links=new ArrayList<>();
            for(final PlaylistTrack track : tracks.getItems()) {
                links.add(track.getTrack().getUri());
            }
            return links.toArray(new String[0]);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }}
    public static JsonArray convertStringArrayToJsonArray(String[] array) {
        Gson gson = new Gson();
        return gson.toJsonTree(array).getAsJsonArray();
    }

    public static void main(String[] args) {
        removeItemsFromPlaylist_Sync();
    }

    private static com.google.gson.JsonParser JsonParser;
    private static final JsonArray tracks = JsonParser.parseString("[{\"uri\":\"spotify:track:01iyCAUm8EvOFqVWYJ3dVX\"}]").getAsJsonArray();


    private static final RemoveItemsFromPlaylistRequest removeItemsFromPlaylistRequest = spotifyApi
            .removeItemsFromPlaylist(playlistId, tracks)
//          .snapshotId("JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+")
            .build();

    public static void removeItemsFromPlaylist_Sync() {
        try {
            final SnapshotResult snapshotResult = removeItemsFromPlaylistRequest.execute();

            System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    }

