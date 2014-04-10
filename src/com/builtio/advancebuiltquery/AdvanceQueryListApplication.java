package com.builtio.advancebuiltquery;

import com.raweng.built.Built;

import android.app.Application;

public class AdvanceQueryListApplication extends Application{

/**
 * This is built.io android tutorial.
 * 
 * Short introduction of some classes with some methods.
 * Contain classes: 
 * 1.BuiltQuery
 * 
 * For quick start with built.io refer "http://docs.built.io/quickstart/index.html#android"
 * For GCM concept refer "http://developer.android.com/google/gcm/gcm.html"
 * 
 * @author raw engineering, Inc
 *
 */
	@Override
	public void onCreate() {
		super.onCreate();
		/*
		 * Initialize the application for a project using built.io Application credentials "Application Api Key" 
		 * and "Application UID".
		 * 
		 */
		try {
			Built.initializeWithApiKey(this, "bltb69ac12834c6339b", "built_query_advanced_demo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
