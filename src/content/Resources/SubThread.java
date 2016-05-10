package content.Resources;

import javax.sound.sampled.FloatControl;

import content.WhatDoesThisDo;

public class SubThread extends Thread {
	static int volume = -80; //Lowest possible volume.

	public static void doThatThing() {
		FloatControl gainControl = (FloatControl) WhatDoesThisDo.clip.getControl(FloatControl.Type.MASTER_GAIN); //The volume control.
		while (gainControl.getValue() < 6) { //Around highest possible value.
			try {
				Thread.sleep(4000); //Increases every four seconds
				volume++; //Increases the volume by one dB.
				if(gainControl.getValue()>=-60 && gainControl.getValue() <= -10){
					volume+=19; //Picks the pace up a little.
				}
				gainControl.setValue(volume); //Adjusts the volume.
			} catch (InterruptedException e) {
			}
		}
	}
}
