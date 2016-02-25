package com.softdesign.school.data.storage.preferences;

        import android.content.SharedPreferences;

        import com.softdesign.school.utils.SchoolApplication;

        import java.util.ArrayList;
        import java.util.List;

public class UserPreferenses {
    private static final String USER_PROFILE_PHONE="phone";
    private static final String USER_PROFILE_EMAIL="email";
    private static final String USER_PROFILE_VK="vk";
    private static final String USER_PROFILE_GIT="git";
    private static final String USER_PROFILE_BIO="bio";
    private static final  String[] USER_FIELDS = {USER_PROFILE_PHONE, USER_PROFILE_EMAIL, USER_PROFILE_VK, USER_PROFILE_GIT, USER_PROFILE_BIO };
    private SharedPreferences mPreferenses;

    public UserPreferenses() {
    }

    public void saveUserProfileData (List<String> userFields){ // сохранение пользовательских данных
        mPreferenses=SchoolApplication.getPreferences();
        SharedPreferences.Editor editor = mPreferenses.edit();
        int i=0;
        for (String field : userFields) {
            editor.putString(USER_FIELDS[i],field);
            i++;
        }
        editor.apply();
    }

    public List<String> loadUserProfileData(){ // загрузка пользовательских данных
        mPreferenses=SchoolApplication.getPreferences();
        List<String> userFields = new ArrayList<>();
        userFields.add(mPreferenses.getString(USER_PROFILE_PHONE, "8 (917) 111-11-11"));
        userFields.add(mPreferenses.getString(USER_PROFILE_EMAIL, "shevtsov_stas@rambler.ru"));
        userFields.add(mPreferenses.getString(USER_PROFILE_VK, "http://vk.com/id2501177"));
        userFields.add(mPreferenses.getString(USER_PROFILE_GIT, "https://github.com/ststlt"));
        userFields.add(mPreferenses.getString(USER_PROFILE_BIO,"pref bio"));
        return userFields;
    }

}