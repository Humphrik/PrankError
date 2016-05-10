package content;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import content.Resources.SubThread;

public class WhatDoesThisDo {
	static JFrame frame = new JFrame("Error");
	static JPanel panel = new JPanel(new GridBagLayout());
	static GridBagConstraints c = new GridBagConstraints();
	static JLabel label = new JLabel("Error 5: Java was unable to run this file");
	static JButton dismiss = new JButton("Dismiss Message");
	public static Clip clip;
	static String relativePath;
	public static void main(String[] args) {
		//Take note of file names, as they must vary between eclipse and the java app.
		try {
			relativePath = new File(".").getCanonicalPath(); //Determines file path for the computer.
			//This is only needed for the executable jar version.
			//IMPORTANT: TO RUN IN ECLIPSE, CHANGE RELATIVE PATH TO "src\\content" (see below)
			relativePath = "src\\content";
		} catch (IOException e1) {
		}
		c.gridy = 0;
		c.insets = new Insets(10,0,10,0);
		//Formatting for panel
		panel.add(label,c);
		c.gridy = 1;
		//Additional formatting for button.
		panel.add(dismiss, c);
		dismiss.setEnabled(false); //It cannot be clicked.
		frame.add(panel);
		frame.setSize(300, 150);
		frame.setResizable(false);
		frame.setVisible(true);
		//Decoration of the window.
		frame.setLocationRelativeTo(null); //Centered on screen.
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		//Important, as it prevents the x button from fully exiting the program.
		
		
		play("Error.wav", 0, 6); //Fake "error" noise.
		play("MainMusic.wav", Clip.LOOP_CONTINUOUSLY, -80); //The music. Starts low....
		SubThread.doThatThing(); //The volume timer (see SubThread).
	}
	public static void play(String filename, int additionalLoops, float volume) { // Creates music for clip.
		//This is what finds and plays the music/sound clip. (still looking for way to use .mp3, not .wav)
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(relativePath+"\\Resources\\" + filename))); //See above about file names
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); //Volume control.
			gainControl.setValue(volume);
			clip.loop(additionalLoops); //Loops for specific extra times.
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
}
