package com.example.ekyc_example

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import com.vnptit.idg.sdk.activity.VnptIdentityActivity
import com.vnptit.idg.sdk.utils.KeyIntentConstants.*
import com.vnptit.idg.sdk.utils.KeyResultConstants.COMPARE_RESULT
import com.vnptit.idg.sdk.utils.KeyResultConstants.FRONT_IMAGE
import com.vnptit.idg.sdk.utils.KeyResultConstants.HASH_FRONT
import com.vnptit.idg.sdk.utils.KeyResultConstants.HASH_PORTRAIT
import com.vnptit.idg.sdk.utils.KeyResultConstants.HASH_REAR
import com.vnptit.idg.sdk.utils.KeyResultConstants.INFO_RESULT
import com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_CARD_FRONT_RESULT
import com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_CARD_REAR_RESULT
import com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_RESULT
import com.vnptit.idg.sdk.utils.KeyResultConstants.MASKED_FACE_RESULT
import com.vnptit.idg.sdk.utils.KeyResultConstants.PORTRAIT_IMAGE
import com.vnptit.idg.sdk.utils.KeyResultConstants.REAR_IMAGE
import com.vnptit.idg.sdk.utils.KeyResultConstants.REGISTER_RESULT
import com.vnptit.idg.sdk.utils.SDKEnum
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.flutter.devekyc/callsdk"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(flutterEngine!!)
        MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "getInformationCard") {
                openEKYC(call, result)
            } else {
                result.notImplemented()
            }
        }
    }

    private fun openEKYC(call: MethodCall, result: MethodChannel.Result) {
        val activity: Activity = this
        if (activity == null) {
            result.error(
                "ACTIVITY_NOT_AVAILABLE", "Browser cannot be opened " +
                        "without foreground activity", null
            )
            return
        }
        val intent = Intent(getActivity(), VnptIdentityActivity::class.java)
        if (intent != null) {
            intent.putExtra(ACCESS_TOKEN, "<input access >")
            intent.putExtra(TOKEN_ID, "<input token id>")
            intent.putExtra(TOKEN_KEY, "<input token key>")
            intent.putExtra(DOCUMENT_TYPE, SDKEnum.DocumentTypeEnum.IDENTITY_CARD.getValue())
            intent.putExtra(SELECT_DOCUMENT, true)
            intent.putExtra(VERSION_SDK, SDKEnum.VersionSDKEnum.ADVANCED.getValue())
            intent.putExtra(SHOW_RESULT, true)
            intent.putExtra(SHOW_DIALOG_SUPPORT, true)
            intent.putExtra(CAMERA_FOR_PORTRAIT, SDKEnum.CameraTypeEnum.FRONT.getValue())
            intent.putExtra(SHOW_SWITCH, false)
            intent.putExtra(CALL_ADD_FACE, false)
            intent.putExtra(LIVENESS_ADVANCED, true)
            intent.putExtra(CHECK_MASKED_FACE, true)
            intent.putExtra(CHECK_LIVENESS_CARD, true)
            intent.putExtra(CHALLENGE_CODE, "<input challenge code>")
            startActivityForResult(intent, 1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val strDataInfo = data.getStringExtra(INFO_RESULT)
                val strDataCompare = data.getStringExtra(COMPARE_RESULT)
                val strDataLiveness = data.getStringExtra(LIVENESS_RESULT)
                val strDataRegister = data.getStringExtra(REGISTER_RESULT)
                val imageFront = data.getStringExtra(FRONT_IMAGE)
                val imageRear = data.getStringExtra(REAR_IMAGE)
                val imagePortrait = data.getStringExtra(PORTRAIT_IMAGE)
                val strLivenessCardFront = data.getStringExtra(LIVENESS_CARD_FRONT_RESULT)
                val strLivenessCardRear = data.getStringExtra(LIVENESS_CARD_REAR_RESULT)
                val strLivenessMaskFace = data.getStringExtra(MASKED_FACE_RESULT)
                val hashFront = data.getStringExtra(HASH_FRONT)
                val hashRear = data.getStringExtra(HASH_REAR)
                val hashPortrait = data.getStringExtra(HASH_PORTRAIT)
            }
        }
    }
}
