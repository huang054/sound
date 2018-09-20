package com.sound.common;

import java.io.File;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.web.multipart.MultipartFile;

public class AudioUtils {

	public static Long getAudioSize(MultipartFile file) {
		Long size=null;
		try {
			File file1 = null;
			file1 = File.createTempFile(file.getName(), ".mp3");
			file.transferTo(file1);
			MP3File f = (MP3File) AudioFileIO.read(file1);
			AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
			size=(long) audioHeader.getTrackLength();
			//System.out.println(audioHeader.getTrackLength());
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return size;
	}
}
