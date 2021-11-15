package com.example.medicosonline.utils

import android.webkit.JavascriptInterface
import com.example.medicosonline.activities.CallActivity

class JavascriptInterface(val callActivity: CallActivity) {

    @JavascriptInterface
    public fun onPeerConnected() {
        callActivity.onPeerConnected()
    }

}
