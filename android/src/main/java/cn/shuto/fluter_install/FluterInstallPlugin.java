package cn.shuto.fluter_install;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FluterInstallPlugin */
public class FluterInstallPlugin implements MethodCallHandler {
  private Context context;

  FluterInstallPlugin(Context context){
    this.context = context;
  };
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "fluter_install");
    channel.setMethodCallHandler(new FluterInstallPlugin(registrar.activity()));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("install")) {
      String path =call.argument("path");
      install(path);
    } else {
      result.notImplemented();
    }
  }

  private void install(String filePath) {
    File apkFile = new File(filePath);
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Log.i("SDK_INT","1111111111");
      intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      Uri contentUri = FileProvider.getUriForFile(
              context
              , "cn.shuto.sinofranch.fileprovider"
              , apkFile);
      intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
    } else {
      Log.i("SDK_INT","22222222222222");
      intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
    }
    context.startActivity(intent);
  }
}
