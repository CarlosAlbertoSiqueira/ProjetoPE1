import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var seconds = 0
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeView: TextView = findViewById(R.id.time_view)
        val startButton: Button = findViewById(R.id.start_button)
        val pauseButton: Button = findViewById(R.id.pause_button)

        startButton.setOnClickListener {
            startChronometer(timeView)
        }

        pauseButton.setOnClickListener {
            pauseChronometer()
        }

        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                updateChronometer(timeView)
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun startChronometer(timeView: TextView) {
        running = true

        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                if (running) {
                    updateChronometer(timeView)
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }

    private fun pauseChronometer() {
        running = false
    }

    private fun updateChronometer(timeView: TextView) {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60

        val time = String.format("%d:%02d:%02d", hours, minutes, secs)
        timeView.text = time

        if (running) {
            seconds++
        }
    }
}
