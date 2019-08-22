package com.neuedu.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

public class SinglePlay extends Thread{

	private String mp3Name;
	
	public SinglePlay() {
		
	}
	public SinglePlay(String mp3Name)
	{
		this.mp3Name = mp3Name;
	}
	
	
	public void Play(String mp3Name)
	{
		SinglePlay singlePlay = new SinglePlay(mp3Name);
		singlePlay.start();
	}
	
	
	@Override
	public void run() {
     InputStream resourceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
		
		try {
			AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
			advancedPlayer.play();
		} catch (JavaLayerException e) {
			
			e.printStackTrace();
		 }
	}
	
}
