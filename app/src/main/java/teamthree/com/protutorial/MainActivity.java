package teamthree.com.protutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn_tutorial);
        button.setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);
        prefs = getSharedPreferences("MYTUT", 0);
        runTutFirst();
    }

    private void runTutFirst() {
        if ( ! prefs.contains("tutrun")) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("tutrun", true);
            runTut();
            editor.commit();
        }
    }

    private void runTut() {
        Intent itn = new Intent(this,TutorialActivity.class);
        startActivity(itn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset_prefs) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("tutrun");
            editor.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit: finish();
                break;
            case R.id.btn_tutorial: runTut();
                break;
        }
    }
}
