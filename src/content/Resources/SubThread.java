package content.Resources;

import javax.sound.sampled.FloatControl;

import content.WhatDoesThisDo;

public class SubThread extends Thread {
	static int volume = -80;

	public static void doThatThing() {
		FloatControl gainControl = (FloatControl) WhatDoesThisDo.clip.getControl(FloatControl.Type.MASTER_GAIN);
		while (gainControl.getValue() < 6) {
			try {
				Thread.sleep(4000);
				volume++;
				if(gainControl.getValue()>=-60 && gainControl.getValue() <= -10){
					volume+=19;
				}
				gainControl.setValue(volume);
			} catch (InterruptedException e) {
			}
		}
	}
}
