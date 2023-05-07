package com.example.pikr.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Store information related to one individual's Login/Profile
 *
 * Use SharedPreference so information can be accessed across activities and app sessions
 */
public class Login {
    private SharedPreferences profile;

    public void clearProfile(){
        profile.edit().clear().apply();
    }

    public Login(Context activityWithInfo){
        profile = PreferenceManager.getDefaultSharedPreferences(activityWithInfo);
    }

    public SharedPreferences getProfile() {
        return profile;
    }

    public void setImageURI(String imageURI){
        profile.edit().putString("profileURI", imageURI).apply();
    }

    public String getImageURI(){
        return profile.getString("profileURI", "empty");
    }

    public void setImagePath(String imagePath){
        profile.edit().putString("profilePath", imagePath).apply();
    }

    public String getImagePath(){
        return profile.getString("profilePath", "empty");
    }

    public String getImage(){
        return profile.getString("profilePic", "empty");
    }

    public void setName(String name){
        profile.edit().putString("name", name).apply();
    }

    public String getName(){
        return profile.getString("name", "empty");
    }

    public void setGender(String gender){
        profile.edit().putString("gender", gender).apply();
    }

    public String getGender(){
        return profile.getString("gender", "empty");
    }

    public void setEmail(String email){
        profile.edit().putString("email", email).apply();
    }

    public String getEmail(){
        return profile.getString("email", "empty");
    }

    public void setPassword(String password){
        profile.edit().putString("password", password).apply();
    }

    public String getPassword(){
        return profile.getString("password", "empty");
    }

    public void setPhone(String phone){
        profile.edit().putString("phone", phone).apply();
    }

    public String getPhone(){
        return profile.getString("phone", "empty");
    }

    public void setMajor(String major){
        profile.edit().putString("major", major).apply();
    }

    public String getMajor(){
        return profile.getString("major", "empty");
    }

    public void setDartClass(String dartClass){
        profile.edit().putString("dartClass", dartClass).apply();
    }

    public String getDartClass() {
        return profile.getString("dartClass", "empty");
    }

    /**
     * Set whether the profile information should be filled in at activity start
     */
    public void setNeedAutofill(boolean isFromLoginActivity){
        profile.edit().putBoolean("fromLoginActivity", isFromLoginActivity).apply();
    }

    /**
     * Get whether the profile information should be filled in at activity start
     */
    public boolean getNeedAutofill(){
        return profile.getBoolean("fromLoginActivity", false);
    }

    /**
     * Set whether the user has opted to post anonymous records
     */
    public void setPrivacySettingEnabled(boolean privacySetting){
        profile.edit().putBoolean("privacySettingEnabled", privacySetting).apply();
    }

    /**
     * Get whether the user has opted to post anonymous records
     */
    public boolean getPrivacySettingEnabled(){
        return profile.getBoolean("privacySettingEnabled", false);
    }

    /**
     * Set what units the user wants to use for their activity information
     */
    public void setUnitPreference(int unitPreference){
        profile.edit().putInt("unit preference", unitPreference).apply();
    }

    /**
     * Get what units the user wants to use for their activity information
     */
    public int getUnitPreference(){
        return profile.getInt("unit preference", -1);
    }
}
