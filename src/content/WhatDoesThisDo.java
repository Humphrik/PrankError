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
		try {
			relativePath = new File(".").getCanonicalPath();
		} catch (IOException e1) {
		}
		c.gridy = 0;
		c.insets = new Insets(10,0,10,0);
		panel.add(label,c);
		c.gridy = 1;
		panel.add(dismiss, c);
		dismiss.setEnabled(false);
		frame.add(panel);
		frame.setSize(300, 150);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		play("Error.wav", 0, 6);
		play("MainMusic.wav", Clip.LOOP_CONTINUOUSLY, -80);
		SubThread.doThatThing();
	}
	public static void play(String filename, int additionalLoops, float volume) { // Creates music for clip.
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(relativePath+"\\Resources\\" + filename)));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			clip.loop(additionalLoops);
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
}
