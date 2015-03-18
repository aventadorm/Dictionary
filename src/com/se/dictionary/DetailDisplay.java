package com.se.dictionary;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

public class DetailDisplay extends Activity {
	TextView wt, mt, st;
	TextToSpeech ttsobj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		TextView wt = (TextView) findViewById(R.id.dw);
		TextView mt = (TextView) findViewById(R.id.dm);
		TextView st = (TextView) findViewById(R.id.ds);

		Bundle bundle = this.getIntent().getExtras();
		wt.setText(bundle.getString("word"));
		mt.setText(bundle.getString("meaning"));
		st.setText(bundle.getString("sentence"));

		// Text To Speech Code
		ttsobj = new TextToSpeech(getApplicationContext(),
				new TextToSpeech.OnInitListener() {
					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR) {
							ttsobj.setLanguage(Locale.US);
							ttsobj.setSpeechRate(0.75f);
							ttsobj.setPitch(1.2f);
							speakText();
						}
					}
				});
		ttsobj.speak("Hi", TextToSpeech.QUEUE_FLUSH, null);
	}

	public void speakText() {
		Bundle bundle = this.getIntent().getExtras();
		String toSpeak = "The word is " + bundle.getString("word")
				+ " which means " + bundle.getString("meaning")
				+ " and an example sentence using " + bundle.getString("word")
				+ " is " + bundle.getString("sentence");
		ttsobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

	}
}
