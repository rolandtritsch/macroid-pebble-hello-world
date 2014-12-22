package org.tritsch.scala.pebble.helloworld

import android.app.Activity
import android.os.Bundle
import android.widget.{TextView, EditText, Button, LinearLayout}
import android.view.Gravity

import macroid._
import macroid.FullDsl._
import macroid.contrib.TextTweaks._

import java.util.UUID

import com.getpebble.android.kit.PebbleKit
import com.getpebble.android.kit.util.PebbleDictionary

object MyTweaks {
  def gravity(g: Int) = {
    Tweak[LinearLayout](_.setGravity(g))
  }
}

object MainActivity {
  val appUuid = UUID.fromString("130b48f3-f990-4458-acc4-8c3887856651")
}

class MainActivity extends Activity with Contexts[Activity] {
  import MyTweaks._

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    var connectionStatus = slot[TextView]
    var helloText = slot[EditText]
    val v = l[LinearLayout](
      w[TextView] <~ wire(connectionStatus) <~ text("???") <~ large,
      w[Button] <~
        text(getString(R.string.check_connection)) <~ large <~
        On.click {
          if(PebbleKit.isWatchConnected(activityAppContext.get)) {
            connectionStatus <~ text(R.string.is_connected) <~ large
          } else {
            connectionStatus <~ text(R.string.is_disconnected) <~ large
          }
        },
      w[EditText] <~ wire(helloText) <~ large,
      w[Button] <~
        text(getString(R.string.send_it)) <~ large <~
        On.click {
          val data = new PebbleDictionary
          data.addString(0, helloText.get.getText.toString)
          PebbleKit.sendDataToPebble(activityAppContext.get, MainActivity.appUuid, data)
          toast("Send it...") <~ fry
        }
    ) <~ vertical <~ gravity(Gravity.CENTER)

    setContentView(getUi(v))
  }
}
