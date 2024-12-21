package com.example.q_2_external_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private Switch  soundSwitch, vibrationSwitch, ledSwitch, bannersSwitch, contentSwitch, lockscreenSwitch;
    private Button saveButton;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundSwitch = findViewById(R.id.switch_sound);
        vibrationSwitch = findViewById(R.id.switch_vibration);
        ledSwitch = findViewById(R.id.switch_led);
        bannersSwitch = findViewById(R.id.switch_banners);
        contentSwitch = findViewById(R.id.switch_content);
        lockscreenSwitch = findViewById(R.id.switch_lockscreen);
        saveButton = findViewById(R.id.btn_save);

        sharedPreferences = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadPreferences();

        saveButton.setOnClickListener(v -> showBottomSheetDialog());
    }

    private void loadPreferences() {

        soundSwitch.setChecked(sharedPreferences.getBoolean("Sound", false));
        vibrationSwitch.setChecked(sharedPreferences.getBoolean("Vibration", true));
        ledSwitch.setChecked(sharedPreferences.getBoolean("LED", true));
        bannersSwitch.setChecked(sharedPreferences.getBoolean("Banners", true));
        contentSwitch.setChecked(sharedPreferences.getBoolean("Content", true));
        lockscreenSwitch.setChecked(sharedPreferences.getBoolean("LockScreen", true));

    }

    private void savePreferences() {

        editor.putBoolean("Sound", soundSwitch.isChecked());
        editor.putBoolean("Vibration", vibrationSwitch.isChecked());
        editor.putBoolean("LED", ledSwitch.isChecked());
        editor.putBoolean("Banners", bannersSwitch.isChecked());
        editor.putBoolean("Content", contentSwitch.isChecked());
        editor.putBoolean("LockScreen", lockscreenSwitch.isChecked());

        editor.apply();
        Toast.makeText(this, "Preferences Saved", Toast.LENGTH_SHORT).show();
    }

    private void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);

        Button confirmButton;
        confirmButton = view.findViewById(R.id.btn_confirm);
        Button cancelButton = view.findViewById(R.id.btn_cancel);

        confirmButton.setOnClickListener(v -> {
            savePreferences();
            bottomSheetDialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.show();

    }
}