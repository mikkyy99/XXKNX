
package com.example.xxknapp.ui.theme;

import android.app.Activity;
import android.os.Build;
import androidx.annotation.NonNull;

/**
 * Theme configuration class for the application.
 */
public class ThemeConfig {
    public static boolean isSystemInDarkTheme() {
        // Placeholder for system theme detection logic
        return false;
    }

    public static void applyTheme(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.setTheme(isSystemInDarkTheme() ? android.R.style.Theme_DeviceDefault_DayNight : android.R.style.Theme_DeviceDefault_Light);
        }
    }
}
