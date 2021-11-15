package com.example.medicosservice

import android.webkit.JavascriptInterface
import com.example.medicosservice.activities.CallActivity

class JavascriptInterface(val callActivity: CallActivity) {

    @JavascriptInterface
    public fun onPeerConnected() {
        callActivity.onPeerConnected()
    }

}
