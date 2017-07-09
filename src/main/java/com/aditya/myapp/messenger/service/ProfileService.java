package com.aditya.myapp.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aditya.myapp.messenger.database.DatabaseClass;
import com.aditya.myapp.messenger.model.Profile;

public class ProfileService {

	private Map <String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("adityam", new Profile(1, "adityam", "Aditya", "More"));
	}
	
	public List<Profile> getProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
