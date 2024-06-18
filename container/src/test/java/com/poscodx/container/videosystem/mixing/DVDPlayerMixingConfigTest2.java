package com.poscodx.container.videosystem.mixing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poscodx.container.config.videosystem.mixing.DVDPlayerConfig;
import com.poscodx.container.videosystem.DVDPlayer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {DVDPlayerConfig.class})
public class DVDPlayerMixingConfigTest2 {
	@Autowired
	private DVDPlayer dvdPlayer;
	
	@Test
	public void testPlay() {
		//assertTrue(1-1==0);
		assertEquals("Playing Movie Marvel's Avengers" , dvdPlayer.play());
	}
}
