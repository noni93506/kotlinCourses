package com.example.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var levelTextView      : TextView
    private lateinit var voltageTextView    : TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var technologyTextView : TextView
private lateinit var capacityTextView:TextView
    private lateinit var healthTextView     : TextView

    private lateinit var chargeTextView     : TextView

    private val batteryReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            intent?.apply {

                val scale = getIntExtra(
                    BatteryManager.EXTRA_SCALE, -1
                )

                val level = getIntExtra(
                    BatteryManager.EXTRA_LEVEL, -1
                )

                val voltage = getIntExtra(
                    BatteryManager.EXTRA_VOLTAGE, 0
                )

                val temperature = getIntExtra(
                    BatteryManager.EXTRA_TEMPERATURE, 0
                )

                val type = getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)

                val health = when (getIntExtra(BatteryManager.EXTRA_HEALTH, -1)){
                    BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
                    BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
                    BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
                    BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Over heat"
                    BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over voltage"
                    BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified"
                    BatteryManager.BATTERY_HEALTH_UNKNOWN -> "Unknown"
                    else -> "Error"
                }

                val status = when (getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
                    BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
                    BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
                    BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
                    BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not charging"
                    BatteryManager.BATTERY_STATUS_FULL -> "Full"
                    else -> "Error"
                }


                levelTextView.text = "Уровень заряда: $level (от 0 to $scale)"
                voltageTextView.text = "Вольтаж: ${voltage / 1000}V"
                temperatureTextView.text = "Температура батареи: ${temperature/ 10}°C"
                technologyTextView.text = "Тип батареи: $type"
                healthTextView.text = "Состояние батареи: $health"
                chargeTextView.text = "Статус: $status"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        levelTextView = findViewById(R.id.mainLevelTextView)
        voltageTextView = findViewById(R.id.mainVoltageTextView)
        temperatureTextView= findViewById(R.id.mainTemperatureTextView)
        technologyTextView= findViewById(R.id.mainTechnologyTextView)
        capacityTextView = findViewById(R.id.mainCapacityTextView)
        healthTextView= findViewById(R.id.mainHealthTextView)

        chargeTextView= findViewById(R.id.mainChargeTextView)
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)


        capacityTextView.text = "Ёмкость батареи: ${getBatteryCapacity()} mAh"
    }

    override fun onPause() {
        unregisterReceiver(batteryReceiver)

        super.onPause()
    }

    private fun getBatteryCapacity(): Double? {
        val powerProfileClass = "com.android.internal.os.PowerProfile"
        try {
            (Class.forName(powerProfileClass).getConstructor(Context::class.java)
                .newInstance(this)).apply {
                    return Class.forName(powerProfileClass)
                        .getMethod("getAveragePower", String::class.java)
                        .invoke(this, "battery.capacity") as Double
                }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}

