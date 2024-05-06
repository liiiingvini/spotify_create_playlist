package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class GetPlaylistsItemsExample {
    private static final String accessToken = "BQCkn3M1JOxr4i7FT0JmQE5a3_qT6mHAwLutoz-30oqkL9ipquxF07vbslmvf6rZSDV1XEviUTcCPHVc29nAef_FORSsQQlRzEFQoFhoc5QWt96BCXXRkQsgd3-ZTO_RyjnoKm4vFOFZPaO-Aeq7SfFD0ko_1SHJcT-Kqxe_yjyjqMZ2bt39PlxihOm2W4L85YXRk72aJtVTZ6wgD5nn7_E80WydHPWiOB31-xmprxt9TPzazRup3idSIUy7w_jO2m9HAUaJzOnhl4kTZ3P8eggrHJSJw_tgLWeRuR2X_g";
    private static final String playlistId = "37i9dQZF1E8PmDsHhHBdar";
    private static final String userId = "a3da87tfi4nm46go75wmaw0xq";
    private static String name;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(playlistId)
            .build();
    private static final GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi
            .getPlaylistsItems(playlistId)
            .build();
    private static final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userId, name)
            .build();


    public static String playlist_getName() {
        try {
            final Playlist playlist = getPlaylistRequest.execute();
            return playlist.getName();
        } catch (SpotifyWebApiException | ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

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
    public static void addItemsToPlaylist_Sync(String[] trackUris) {
        name = playlist_getName() + "_copy";
        CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userId, name)
            .build();
        String pl_id= null;
        try {
            pl_id = createPlaylistRequest.execute().getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
                .addItemsToPlaylist(pl_id,trackUris)
        .build();
        try {
            final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

            System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        addItemsToPlaylist_Sync(getPlaylistsItems_Sync());
    }
}

