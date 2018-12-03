package application;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.function.DoubleFunction;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class LoopingAudioPlayer implements Runnable {
	
	private URL streamUrl;
	private boolean stop = false;
	private Runnable onStop;
	private DoubleFunction<Void> updateTime;
	
	public LoopingAudioPlayer(URL url, DoubleFunction<Void> function) {
		streamUrl = url;
		this.updateTime = function;
	}
	
	@Override
	public void run() {
		
		Logger.getInstance().log("Play music");
		AudioInputStream din = null;
		SourceDataLine line = null;
		
	    try(AudioInputStream in = AudioSystem.getAudioInputStream(new BufferedInputStream(streamUrl.openStream()))) {

	        AudioFormat baseFormat = in.getFormat();
	        AudioFormat decodedFormat = new AudioFormat(
	                AudioFormat.Encoding.PCM_SIGNED,
	                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
	                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
	                false);
	        din = AudioSystem.getAudioInputStream(decodedFormat, in);
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
	        line = (SourceDataLine) AudioSystem.getLine(info);

	        if(line != null) {
	            readFromLine(line, decodedFormat, din);
	        }

	    }
	    catch(Exception e) {
	        Logger.getInstance().log(e.getStackTrace().toString());
	    }
	    finally {
	        if(din != null) {
	            try { din.close(); } catch(IOException e) {
	            	Logger.getInstance().log(e.getStackTrace().toString());
	            }
	        }
	        if(line != null) {
	            line.drain();
	            line.stop();
	            line.close();
	        }
	        
	        if(!stop) {
	        	run();
	        } else if(onStop != null) {
	        	onStop.run();
	        }
	    }
	}
	
	public void stop() {
		this.stop = true;
	}
	
	public void stop(Runnable callback) {
		stop();
		this.onStop = callback;
	}
	
	private void readFromLine(SourceDataLine line, AudioFormat decodedFormat, AudioInputStream din) throws LineUnavailableException, IOException {
		
		line.open(decodedFormat);
        byte[] data = new byte[4096];
        // Start
        line.start();
        
        FloatControl control = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);

        int totalBytes = 0;
        int nBytesRead;
        
        AudioFormat fmt = din.getFormat();
        long totalFrames = din.getFrameLength();
        long frameSize = fmt.getFrameSize();
        double totalSeconds = (double) totalFrames / fmt.getSampleRate();
        
        double initialGain = control.getValue();
        double startFadeOut = 29;
        boolean startedStop = false;
        
        while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
        	
        	totalBytes += nBytesRead;
            long framesRead = totalBytes / frameSize;
            double elapsedSeconds = ((double) framesRead / (double) totalFrames) * totalSeconds;

            if(!startedStop) {
				updateTime.apply(elapsedSeconds);
			}
            
            if(stop && !startedStop) {
            	if(elapsedSeconds < 29) {
            		startFadeOut = elapsedSeconds;
            	}
            	startedStop = true;
            } 
            
            double gain = getFadeGain(0,1,startFadeOut,startFadeOut+1,
            		control.getMinimum(),initialGain,
            		elapsedSeconds);
            
            if(startedStop && gain == control.getMinimum()) {
            	break;
            }
            
            control.setValue((float) gain);
            
            line.write(data, 0, nBytesRead);
        }
	}
	
	private double getFadeGain(double startA, double endA, double startB, double endB, double min, double max, double elapsedSeconds) {
        double gainStart = rangeMap(elapsedSeconds, startA, endA, min, max);
        double gainStop = rangeMap(elapsedSeconds, startB, endB, max, min);
        
        if(gainStop != max) {
        	return gainStop;
        }
        
        return gainStart;
	}
	
	private double rangeMap(double oldValue, double oldMin, double oldMax, double newMin, double newMax) {
		if(oldValue < oldMin) {
			oldValue = oldMin;
		} else if (oldValue > oldMax) {
			oldValue = oldMax;
		}
		return ((oldValue - oldMin) / (oldMax - oldMin))
		          * (newMax - newMin) + newMin;
	}

}