package padtm.lab_3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.provider.MediaStore;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Random;

import padtm.lab_3.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Kliknięto przycisk FAB", Toast.LENGTH_SHORT)
                        .show();
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                 */
            }
        });
    }

    public void click(View view)
    {
        Toast.makeText(getApplicationContext(),"Kliknięto przycisk Button", Toast.LENGTH_SHORT)
                .show();
    }

    public void randomButtonImageChange(View view)
    {
        Button menuButton = (Button)findViewById(R.id.button2);
        final Random random = new Random();
        int randomNumber  = random.nextInt(1);
        final Class drawableClass = ((randomNumber == 0) ? android.R.drawable.class : R.drawable.class);
        final Field[] fields = drawableClass.getFields();
        randomNumber  = random.nextInt(fields.length);
        try {
            int resourceId = fields[randomNumber].getInt(drawableClass);
            menuButton.setBackground(getResources().getDrawable(resourceId, getTheme()));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"Kliknięto przycisk Settings", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_one:
                Toast.makeText(getApplicationContext(),"Kliknięto przycisk One", Toast.LENGTH_SHORT)
                        .show();
                Intent intencja_login = new Intent(this, LoginActivity.class);
                startActivity(intencja_login);
                break;
            case R.id.action_two:
                Toast.makeText(getApplicationContext(),"Kliknięto przycisk Two", Toast.LENGTH_SHORT)
                        .show();
                Intent intencja_aparat = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intencja_aparat, REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.action_three:
                Toast.makeText(getApplicationContext(),"Kliknięto przycisk Three", Toast.LENGTH_SHORT)
                        .show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //super.onActivityResult();
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        ConstraintLayout lay = (ConstraintLayout)findViewById(R.id.cont);
        lay.setBackground(new BitmapDrawable(getResources(), imageBitmap));
    }
}