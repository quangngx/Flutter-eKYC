package com.example.vnpt_sdk_ekyc
import androidx.annotation.NonNull
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
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** VnptSdkEkycPlugin */
class VnptSdkEkycPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "vnpt_sdk_ekyc")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getInformationCard") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
      openEKYC(call, result)
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
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
      intent.putExtra(ACCESS_TOKEN, "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIwMWQ0Zjg1OC05MjQ2LTNiOTEtZTA2My02MjE5OWYwYTg4NjYiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoidGVjaC5xdWFuZ25uQGFuZWVkLnZuIiwic2NvcGUiOlsicmVhZCJdLCJpc3MiOiJodHRwczovL2xvY2FsaG9zdCIsIm5hbWUiOiJ0ZWNoLnF1YW5nbm5AYW5lZWQudm4iLCJ1dWlkX2FjY291bnQiOiIwMWQ0Zjg1OC05MjQ2LTNiOTEtZTA2My02MjE5OWYwYTg4NjYiLCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjU3MWU2M2ExLTRkNGUtNDZlNy1iMzBlLTY4NzNkOWNmYjgzYyIsImNsaWVudF9pZCI6ImFkbWluYXBwIn0.5xNTKzc3rpPGs2xFaXFfElKmQRykYTXtahlkblYQfffZybZ1kTvz-CJigIW7WT6z7tXJ1_s9Ndzgpmq1KnRkNgIOuEMnqre7BvH595IRl2ZnMKX6dXK1KdnpjSdRv_2usW2sN4pwMyhBMwExzcLMOecrZPImFpmsyelEEAlhaLffGMJR58ONDDA26__3FRXVI20UweIAPKQSf5R0PahNXmNkaEm5wCwn8MyWb2vLfTBrqQjmcZRR3RNiekU5VDzHCCQ2pvIEjg44OVaZ67we-mi-c0OvU8GmDMonYegCxOljh5ygFUO-tZyyH_9y0z_aU371ExCEjWqLpnAGQ9V1AA")
      intent.putExtra(TOKEN_ID, "01d58710-720a-2e81-e063-62199f0a9d5f")
      intent.putExtra(TOKEN_KEY, "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKPPS8sTctKjf6XMKXqAbSARctU0eQu5pP5LoUVFC32jfULFKvR9/o4coa2RZ1vldfhcXO/CGucI9p25K9FwlRsCAwEAAQ==")
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
