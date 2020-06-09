package com.google.sps.servlets;

//String to JSON import
import com.google.gson.Gson;

//Spotify Web API library import
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import org.apache.hc.core5.http.ParseException;

//Java library imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader; 
import java.util.Random;

/** Servlet returns the name of a random song from @tedim52 personal playlist: "The Playlist" */
@WebServlet("/randomsong")
public class RandomSongServlet extends HttpServlet {
	private static final String clientId = "fcc609c17011467f86b11b74bab2f100";
    private static final String clientSecret = "0944680429aa4f95900bad1dd85cd7c6";
    private static final String playlistId = "0oAcmortY6uEwcE3ONHFSJ";
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    SpotifyApi spotifyApi = new SpotifyApi.Builder()
    	    .setClientId(clientId)
    	    .setClientSecret(clientSecret)
    	    .build();
    	clientCredentials_Sync(spotifyApi);
        String randomSong = getRandomSongName(spotifyApi);
    	response.setContentType("text/html");
        response.getWriter().println(randomSong);
   
    }
	
    /* Requesting access token from Spotify Web API for further usage of the "spotifyApi" library */
    public static void clientCredentials_Sync(SpotifyApi spotifyApi) {
    	try {
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
    	    .build(); 
      	    ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            
      	    spotifyApi.setAccessToken(clientCredentials.getAccessToken());
      	    System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    	} catch (IOException | SpotifyWebApiException | ParseException e) {
      	    System.out.println("Error: " + e.getMessage());
    	}
    }

    public static String getRandomSongName(SpotifyApi spotifyApi) {
        try {
            GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi
    	    .getPlaylistsItems(playlistId)
    	    .build();
      	    PlaylistTrack[] playlist = getPlaylistsItemsRequest.execute().getItems();
           
            Random rand = new Random(); 
            PlaylistTrack pagedTrack = playlist[rand.nextInt(playlist.length)];
            Track randomTrack = (Track) pagedTrack.getTrack();
      	    return randomTrack.getName();
    	} catch (IOException | SpotifyWebApiException | ParseException e) {
	        System.out.println("Error: " + e.getMessage());
            return "";
    	}
    }
}
