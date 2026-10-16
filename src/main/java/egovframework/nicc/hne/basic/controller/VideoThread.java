package egovframework.nicc.hne.basic.controller;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

public class VideoThread extends Thread {
	
	private File source;
	private String video_filenm;
	private String root_patch;
	
	public VideoThread(File source, String video_filenm, String root_patch) {
		this.source = source;
		this.video_filenm = video_filenm;
		this.root_patch = root_patch;
	}

	public void run() {
		FrameGrab grab;

		try {
			grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));

			double startSec = 0;
			int frameCount = 1;
			String url = root_patch+"/resources/upload/thumbnail";
			
			File Folder = new File(url);
			if (!Folder.exists()) {
				Folder.mkdir();
			}
			
			grab.seekToSecondPrecise(startSec);

			for (int i = 0; i < frameCount; i++) {
				Picture picture = grab.getNativeFrame();
				
				BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
				ImageIO.write(bufferedImage, "png", new File(url + "\\" + video_filenm.replace(".mp4", "") + "_thumbnail.png"));
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}